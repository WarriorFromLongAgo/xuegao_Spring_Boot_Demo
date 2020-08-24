package com.xuegao.springboot_tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.IProductDao;
import com.xuegao.springboot_tool.dao.UserInfoMapper;
import com.xuegao.springboot_tool.model.doo.Product;
import com.xuegao.springboot_tool.service.interfaces.IProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<IProductDao, Product> implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 秒杀商品
     * @param productId 商品ID
     * @param number    数量
     */
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
            product.setStocks(product.getStocks() - number);
            productDao.updateById(product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }
}