package com.xuegao.netty_chat_room_server.mininetty;

import com.xuegao.netty_chat_room_server.mininetty.pool.Boss;
import com.xuegao.netty_chat_room_server.mininetty.pool.NioSelectorRunablePool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class ServerBootstrap {

	private NioSelectorRunablePool selectorRunablePool;
	
	public ServerBootstrap(NioSelectorRunablePool pool) {
		this.selectorRunablePool = pool;
	}

	public void bind(final InetSocketAddress inetSocketAddress) {
		try {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(inetSocketAddress);
			
			// ��ȡһ��boss�߳�
			Boss boss = selectorRunablePool.nextBoss();
			boss.registerAcceptChannelTask(serverChannel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
