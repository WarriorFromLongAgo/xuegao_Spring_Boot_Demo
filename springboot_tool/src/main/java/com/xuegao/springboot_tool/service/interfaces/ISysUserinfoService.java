package com.xuegao.springboot_tool.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.springboot_tool.model.PageInfo;
import com.xuegao.springboot_tool.model.PageQuery;
import com.xuegao.springboot_tool.model.doo.SysUserinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service
 * <br/> @ClassName：ISysUserinfoService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/7/14 20:05
 */
public interface ISysUserinfoService extends IService<SysUserinfo> {

    Logger LOG = LoggerFactory.getLogger(ISysUserinfoService.class);


    PageInfo<SysUserinfo> page2(PageQuery<SysUserinfo> pageQuery);

    PageInfo<SysUserinfo> page3(PageQuery<SysUserinfo> pageQuery);

}