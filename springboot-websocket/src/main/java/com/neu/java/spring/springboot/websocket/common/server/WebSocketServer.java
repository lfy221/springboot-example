package com.neu.java.spring.springboot.websocket.common.server;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.neu.java.spring.springboot.websocket.common.model.Result;
import com.neu.java.spring.springboot.websocket.common.util.MessageDecoder;
import com.neu.java.spring.springboot.websocket.common.util.MessageEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.EncodeException;
import javax.websocket.OnOpen;
import javax.websocket.Session;
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

    public static synchronized int getOnlineCount() {
        return onlineNum.get();
    }

    public static synchronized void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}
