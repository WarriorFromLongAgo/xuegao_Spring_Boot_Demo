package com.xuegao.springboot_tool.单元测试;

import com.xuegao.springboot_tool.model.doo.Product;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    private final static Logger log = LoggerFactory.getLogger(ProductControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    @Before
    public void initProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("秒杀");
        product.setPrice("100.00");
        product.setStocks(10000);
        product.setDescription("秒杀商品");
        productService.initProduct(product);
    }

    //测试get接口
    @Test
    public void seckillProductTest() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/product/seckillTest")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse()
                .getContentAsString();
        Assert.assertEquals("调用成功", "创建订单成功", result);
    }

    @Test
    public void seckillProductTest1() {
        boolean aBoolean = productService.seckillProduct(1L, 1);
        Assert.assertTrue("调用成功", aBoolean);
    }

    @Test
    public void jucCountDownLatchSeckill() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            productService.seckillProduct(
                                    ThreadLocalRandom.current().nextLong(1,3),
                                    ThreadLocalRandom.current().nextInt(1, 100));
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        long startTime = System.nanoTime();
        log.info(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        log.info(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
    }

    @Test
    public void jucThreadPoolExecutorSeckill() {
        // ExecutorService executorService = new ThreadPoolExecutor(100,
        //         200, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20000));
        // for (int i = 0; i < 100; i++) {
        //     executorService.submit(new Runnable() {
        //         @Override
        //         public void run() {
        //             productService.seckillProduct(1L, 1);
        //         }
        //     });
        // }
    }

    @Test
    public void jucCyclicBarrierSeckill() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        ExecutorService executorService = new ThreadPoolExecutor(10,
                100, 60, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    productService.seckillProduct(1L, 1);
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //通过打印日志 得出它具有自动重置功能
                log.info("Parties={} 等待中{}", cyclicBarrier.getParties(), cyclicBarrier.getNumberWaiting());
            });
            executorService.shutdown();
        }
    }

    private static final int THREAD_COUNT = 100;
    private static final int CONCURRENT_COUNT = 5;
    private static volatile int COUNT = 0;

    @Test
    public void jucSemaphoreSeckill() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(CONCURRENT_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    COUNT++;
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(COUNT);
    }
}