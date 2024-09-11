package com.neu.java.spring.springboot.websocket.common.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Java端websocket client
 */
public class Client extends WebSocketClient {

    public Client(URI serverURI) {
        super(serverURI);
    }

    public Client(URI serverURI, Draft draft) {
        super(serverURI, draft);
    }

    public Client(URI serverURI, Map<String, String> httpHeaders) {
        super(serverURI, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        send("{\"msg\":\"formclient\"}");
        System.out.println("open connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onMessage(ByteBuffer byteBuffer) {
        System.out.println("received: bytebuffer" );
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("ws://localhost:10001/ws-demo/websocket/abc");
        Client client = new Client(uri);
        client.connect();
    }
}
