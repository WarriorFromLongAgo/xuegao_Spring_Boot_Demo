package com.xuegao.springboot_tool.manager;

import com.xuegao.springboot_tool.dao.IYzyRepoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.dao
 * <br/> @ClassName：IYzyRepoMapper
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/29 18:36
 */
@Component
public class YzyRepoManager {

    private final IYzyRepoMapper yzyRepoMapper;

    @Autowired
    public YzyRepoManager(IYzyRepoMapper yzyRepoMapper) {
        this.yzyRepoMapper = yzyRepoMapper;
    }



}