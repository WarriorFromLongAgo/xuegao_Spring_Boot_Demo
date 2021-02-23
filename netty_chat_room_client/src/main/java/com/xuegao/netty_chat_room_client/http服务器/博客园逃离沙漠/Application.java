package com.xuegao.netty_chat_room_client.http服务器.博客园逃离沙漠;

public class Application {

    public static void main(String[] args) throws Exception {
        // http://localhost:12000/index
        // http://localhost:12000/index
        HttpServer server = new HttpServer(12000);// 8081为启动端口
        server.start();
    }
}
