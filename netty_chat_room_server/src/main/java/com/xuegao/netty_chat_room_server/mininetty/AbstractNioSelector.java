package com.xuegao.netty_chat_room_server.mininetty;

import com.xuegao.netty_chat_room_server.mininetty.pool.NioSelectorRunablePool;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ����selector�߳���
 */
public abstract class AbstractNioSelector implements Runnable{


	/**
	 * �̳߳�
	 */
	private final Executor executor;
	
	/**
	 * ѡ����
	 */
	protected Selector selector;
	
	/**
	 * ѡ����wakenUp״̬���
	 */
	protected final AtomicBoolean wakenUp = new AtomicBoolean();
	
	/**
	 * �������
	 */
	private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<Runnable>();
	
	/**
	 * �߳�����
	 */
	protected String threadName;
	
	private NioSelectorRunablePool selectorPool;
	
	AbstractNioSelector(Executor executor, String threadName, 
			NioSelectorRunablePool nioSelectorRunablePool) {
		this.executor = executor;
		this.threadName = threadName;
		this.selectorPool = nioSelectorRunablePool;
		openSelector();
	}

	/**
	 * ��ȡselector�������߳�
	 */
	private void openSelector() {
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			throw new RuntimeException("Failed to create a selector.");
		}
		executor.execute(this);
	}

	@Override
	public void run() {
		Thread.currentThread().setName(this.threadName);
		while(true) {
			try {
				wakenUp.set(false);
				
				select(selector);
				
				processTaskQueue();
				
				process(selector);
			} catch (Exception e) {
				// ignore;
			}
		}
	}

	protected abstract void process(Selector selector) throws IOException ;
	
	protected abstract int select(Selector selector) throws IOException;

	private void processTaskQueue() {
		for (;;) {
			final Runnable task = taskQueue.poll();
			if (task == null) {
				break;
			}
			task.run();
		}
	}
	
	/**
	 * ��ȡ�̹߳������
	 */
	public NioSelectorRunablePool getSelectorRunnablePool() {
		return selectorPool;
	}

	/**
	 * ע��һ�����񲢼���selector
	 */
	protected final void registerTask(Runnable task) {

		taskQueue.add(task);
		
		Selector selector = this.selector;
		
		if (selector != null) {
			if (wakenUp.compareAndSet(false, true)) {
				selector.wakeup();
			}
		} else {
			taskQueue.remove(task);
		}
	}

}
