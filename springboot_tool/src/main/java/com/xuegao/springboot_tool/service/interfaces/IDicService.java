package com.xuegao.springboot_tool.service.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuegao.springboot_tool.model.doo.Dic;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IDicService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/28 16:24
 */
public interface IDicService extends IService<Dic> {

    Dic getDicById(Long id);

    List<Dic> dicList(List<Long> idList);
}