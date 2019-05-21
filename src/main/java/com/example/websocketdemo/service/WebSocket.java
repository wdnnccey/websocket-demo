package com.example.websocketdemo.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        System.out.println("新的连接 总数:"+webSocketSet.size());
    }


    @OnClose
    public void onColse(){
        webSocketSet.remove(this);
        System.out.println("断开连接 总数:"+webSocketSet.size());

    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("收到客户端发来的消息:"+message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSet){
            System.out.println("广播消息:"+message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}