package com.xuegao.springboot_tool.service.interfaces;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IProductService
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/24 10:14
 */
public interface IProductService {

    Boolean seckillProduct(Long productId, Integer number);
}