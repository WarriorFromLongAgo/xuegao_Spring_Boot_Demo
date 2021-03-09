package com.xuegao.netty_chat_room_server.mininetty.pool;

import com.xuegao.netty_chat_room_server.mininetty.NioServerBoss;
import com.xuegao.netty_chat_room_server.mininetty.NioServerWorker;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �����̳߳�
 */
public class NioSelectorRunablePool {

    /**
     * boss�߳�����
     */
    private final AtomicInteger bossIndex = new AtomicInteger();
    private Boss[] bosses;
    /**
     * worker�߳�����
     */
    private final AtomicInteger workerIndex = new AtomicInteger();
    private Worker[] workeres;


    public NioSelectorRunablePool(Executor boss, Executor worker) {
        initBoss(boss, 1);
        initWorker(worker, Runtime.getRuntime().availableProcessors() * 2);
    }

    /**
     * ��ʼ��boss�߳�
     */
    private void initBoss(Executor boss, int count) {
        this.bosses = new NioServerBoss[count];
        for (int i = 0; i < bosses.length; i++) {
            bosses[i] = new NioServerBoss(boss, "boss thread " + (i + 1), this);
        }
    }

    /**
     * ��ʼ��worker�߳�
     */
    private void initWorker(Executor worker, int count) {
        this.workeres = new NioServerWorker[count];
        for (int i = 0; i < workeres.length; i++) {
            workeres[i] = new NioServerWorker(worker, "worker thread " + (i + 1), this);
        }
    }

    /**
     * ��ȡһ��worker
     */
    public Worker nextWorker() {
        return workeres[Math.abs(workerIndex.getAndIncrement() % workeres.length)];
    }

    /**
     * ��ȡһ��boss
     */
    public Boss nextBoss() {
        return bosses[Math.abs(bossIndex.getAndIncrement() % bosses.length)];
    }

}
