package com.neu.java.spring.springboot.websocket.common.server;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.neu.java.spring.springboot.websocket.common.model.Result;
import com.neu.java.spring.springboot.websocket.common.util.MessageDecoder;
import com.neu.java.spring.springboot.websocket.common.util.MessageEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint(value = "/websocket/{sid}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class WebSocketServer {
    private static Log log = LogFactory.get();
    /**静态变量，用来记录当前在线连接数，线程安全。*/
    private static AtomicInteger onlineNum = new AtomicInteger();
    /**concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。*/
    private static ConcurrentHashMap<String, WebSocketServer> websocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收频道sid*/
    private String sid = "";

    /**
     * 连接建立
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        this.sid = sid;
        if(websocketMap.containsKey(sid)) {
            websocketMap.remove(sid);
            websocketMap.put(sid, this);
        } else {
            websocketMap.put(sid, this);
            addOnlineCount();
        }

        log.info("新客户端接入频道{}，当前在线数为：{}", sid, getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error(e, "新的客户端接入频道{}异常", sid);
        }
    }


    /**
     * 收到客户端消息后广播到toSid
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自频道{}终端的消息：{}", sid, message);
        try {
            if(StrUtil.isNotBlank(message)) {
                JSONObject jsonObject = new JSONObject(message);
                jsonObject.set("fromSid", this.sid);
                String toSid = jsonObject.getStr("toSid");
                if(hasSid(toSid)) {
                    websocketMap.get(toSid).sendMessage(jsonObject.toStringPretty());
                } else {
                    log.info("获取不到要发送的sid，消息：{}", message);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        if(websocketMap.containsKey(sid)) {
            websocketMap.remove(sid);
            subOnlineCount();
            log.info("客户端退出频道{}，当前在线数为{}", sid, getOnlineCount());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("客户端{}发生错误，原因：{}", sid, error.getMessage());
        log.error(error);
    }

    /**
     * 实现服务器主动推送消息
     */
    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 实现服务器主动推送对象
     */
    public void sendObject(Result result) throws IOException, EncodeException {
        synchronized (session) {
            this.session.getBasicRemote().sendObject(result);
        }
    }

    /**
     * 实现服务器主动推送二进制对象
     */
    public void sendBinary(ByteBuffer data) throws IOException{
        synchronized (session) {
            this.session.getBasicRemote().sendBinary(data);
        }
    }

    /**
     * 发送指定图片
     */
    public void sendPicture(WebSocketSession session, String fileName) {
        FileInputStream input;
        try {
            //TODO 待测试验证，路径存放配置文件
            File file = new File("D:\\images\\" + fileName);
            input = new FileInputStream(file);
            byte bytes[] = new byte[(int)file.length()];
            input.read(bytes);

            BinaryMessage binaryMessage = new BinaryMessage(bytes);
            session.sendMessage(binaryMessage);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 往指定频道发送消息
     */
    public static void sendMessageToSid(String message, @PathParam("sid") String sid) {
        try {
            log.info("发送消息到客户端:{}，报文:{}", sid, message);
            if(hasSid(sid)) {
                websocketMap.get(sid).sendMessage(message);
            } else {
                log.error("客户端{}不在线", sid);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 往指定频道发送对象消息
     */
    public static void sendObjectToSid(Result result, @PathParam("sid") String sid) {
        try {
            log.info("发送对象消息到客户端:{}，对象:{}", sid, result);
            if(hasSid(sid)) {
                websocketMap.get(sid).sendObject(result);
            } else {
                log.error("客户端{}不在线", sid);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 往指定频道发送二进制数据
     */
    public static void sendBinaryToSid(ByteBuffer data, @PathParam("sid") String sid) {
        try {
            log.info("发送二进制数据到客户端:{}", sid);
            if(hasSid(sid)) {
                websocketMap.get(sid).sendBinary(data);
            } else {
                log.error("客户端{}不在线", sid);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNum.get();
    }

    public static synchronized void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    private static boolean hasSid(String sid) {
        return StrUtil.isNotBlank(sid) && websocketMap.containsKey(sid);
    }
}
