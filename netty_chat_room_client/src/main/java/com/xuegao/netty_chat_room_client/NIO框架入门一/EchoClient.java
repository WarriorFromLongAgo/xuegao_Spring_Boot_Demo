package com.xuegao.netty_chat_room_client.NIO框架入门一;

import com.xuegao.netty_chat_room_client.NIO框架入门一.utils.Log;
import com.xuegao.netty_chat_room_client.NIO框架入门一.utils.UDPUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.NIO框架入门一
 * <br/> @ClassName：EchoClient
 * <br/> @Description：客户端主类 EchoClient
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 19:55
 */
public class EchoClient {
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        // 初始化本地UDP的Socket
        LocalUDPSocketProvider.getInstance().initSocket();
        // 启动本地UDP监听（接收数据用的）
        LocalUDPDataReciever.getInstance().startup();

        // 循环发送数据给服务端
        while (true) {
            // 要发送的数据
            String toServer = "Hi，我是客户端，我的时间戳" + System.currentTimeMillis();
            byte[] soServerBytes = toServer.getBytes(StandardCharsets.UTF_8);

            // 开始发送
            boolean ok = UDPUtils.send(soServerBytes, soServerBytes.length);
            if (ok) {
                Log.d("EchoClient", "发往服务端的信息已送出.");
            } else {
                Log.e("EchoClient", "发往服务端的信息没有成功发出！！！");
            }
            // 3000秒后进入下一次循环
            Thread.sleep(3000);
        }
    }
}