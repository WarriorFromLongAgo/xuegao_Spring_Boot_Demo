package com.xuegao.springboot_tool.service.interfaces;

import com.xuegao.springboot_tool.model.doo.Order;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IMysqlService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/05 22:52
 */
public interface IMysqlService {

    void transactional();

    Long insert1();

    Long insert2();

    Long insert3();

    Order getById();

}