package com.xuegao.springboot_tool.service.interfaces;

import com.xuegao.springboot_tool.model.doo.MyJvm;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IMyJvmService
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/9 10:53
 */
public interface IMyJvmService extends BaseService<MyJvm> {

    MyJvm getMyJvmById(Long id);

    void cpu100(Long id);

    void stackOverflowError();

    void outOfMemoryError();
}