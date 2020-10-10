package com.xuegao.springboot_tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.IProductDao;
import com.xuegao.springboot_tool.model.doo.Product;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import com.xuegao.springboot_tool.utils.KafkaUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl extends ServiceImpl<IProductDao, Product> implements IProductService {

    private IProductDao productDao;

    @Autowired
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    private RedissonClient redissonClient;

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private KafkaUtil kafkaUtil;

    @Autowired
    public void setKafkaUtil(KafkaUtil kafkaUtil) {
        this.kafkaUtil = kafkaUtil;
    }

    /**
     * 秒杀商品
     * @param productId 商品ID
     * @param number    数量
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean seckillProduct(Long productId, Integer number) {
        String key = "seckill_stock_lock_" + productId;
        RLock lock = redissonClient.getLock(key);
        try {
            //获取分布式锁
            lock.lock();
            Product product = productDao.selectById(productId);
            if (ObjectUtils.isEmpty(product)) {
                return false;
            }
            Integer stocks = product.getStocks();
            if (stocks < number) {
                return false;
            }
            product.setStocks(product.getStocks() - number);
            productDao.updateById(product);
            kafkaUtil.sendMessage(productId, number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean seckillProduct2(Long productId, Integer number) {
        String key = "seckill_stock_lock_" + productId;
        RLock lock = redissonClient.getLock(key);
        try {
            //获取分布式锁
            lock.lock();
            Product product = productDao.selectById(productId);
            if (ObjectUtils.isEmpty(product)) {
                return false;
            }
            Integer stocks = product.getStocks();
            if (stocks < number) {
                return false;
            }
            product.setStocks(product.getStocks() - number);
            productDao.updateById(product);
            kafkaUtil.sendMessage(productId, number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public void initProduct(Product product) {
        System.out.println("=====================================================================");
        System.out.println("=====================================================================");
        productDao.deleteAllOrder();
        System.out.println(product);
        int i = productDao.updateById(product);
        System.out.println(i);
        System.out.println("=====================================================================");
        System.out.println("=====================================================================");
    }
}