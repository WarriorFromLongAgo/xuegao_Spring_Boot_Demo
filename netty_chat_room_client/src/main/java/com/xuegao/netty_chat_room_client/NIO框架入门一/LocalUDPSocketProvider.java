package com.xuegao.netty_chat_room_client.NIO框架入门一;

import com.xuegao.netty_chat_room_client.NIO框架入门一.utils.ConfigEntity;
import com.xuegao.netty_chat_room_client.NIO框架入门一.utils.Log;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <br/> @PackageName：com.xuegao.netty_chat_room_client.NIO框架入门一
 * <br/> @ClassName：LocalUDPSocketProvider
 * <br/> @Description：Socket操作类
 * <br/> @author：xuegao
 * <br/> @date：2020/10/28 19:56
 */
public class LocalUDPSocketProvider {
    private static final String TAG = LocalUDPSocketProvider.class.getSimpleName();
    private static LocalUDPSocketProvider instance = null;
    private DatagramSocket localUDPSocket = null;

    public static LocalUDPSocketProvider getInstance() {
        if (instance == null) {
            instance = new LocalUDPSocketProvider();
        }
        return instance;
    }

    public DatagramSocket getLocalUDPSocket() {
        return this.localUDPSocket;
    }

    public void initSocket() {
        try {
            // UDP本地监听端口（如果为0将表示由系统分配，否则使用指定端口）
            this.localUDPSocket = new DatagramSocket(ConfigEntity.localUDPPort);
            // 调用connect之后，每次send时DatagramPacket就不需要设计目标主机的ip和port了
            // * 注意：connect方法一定要在DatagramSocket.receive()方法之前调用，
            // * 不然整send数据将会被错误地阻塞。这或许是官方API的bug，也或许是调
            // * 用规范就应该这样，但没有找到官方明确的说明
            this.localUDPSocket.connect(InetAddress.getByName(ConfigEntity.serverIP), ConfigEntity.serverUDPPort);
            this.localUDPSocket.setReuseAddress(true);
            Log.d(TAG, "new DatagramSocket()已成功完成.");

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}