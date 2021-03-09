package com.xuegao.netty_chat_room_server.mininetty;

import com.xuegao.netty_chat_room_server.mininetty.pool.NioSelectorRunablePool;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Starter {

	public static void main(String[] args) {

		// ��ʼ���̳߳�
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		NioSelectorRunablePool pool = new NioSelectorRunablePool(boss, worker);
		
		// ��ȡ������
		ServerBootstrap bootstrap = new ServerBootstrap(pool);
		
		// �󶨶˿�
		bootstrap.bind(new InetSocketAddress(10101));
		
		System.out.println("Mini netty started !!!");
		
	}

}
