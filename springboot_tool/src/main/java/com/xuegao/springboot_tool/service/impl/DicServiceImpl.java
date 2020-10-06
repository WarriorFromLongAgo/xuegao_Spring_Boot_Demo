package com.xuegao.springboot_tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuegao.springboot_tool.dao.IDicMapper;
import com.xuegao.springboot_tool.model.doo.Dic;
import com.xuegao.springboot_tool.service.interfaces.IDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：DicServiceImpl
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/9/28 16:24
 */
@Service
public class DicServiceImpl extends ServiceImpl<IDicMapper, Dic> implements IDicService {
    private Logger log = LoggerFactory.getLogger(DicServiceImpl.class);

    @Override
    public Dic getDicById(Long id) {
        Dic dic = getById(id);
        log.info("DicServiceImpl getDicById {}", dic);
        return dic;
    }

    @Override
    public List<Dic> dicList(List<Long> idList) {
        List<Dic> dics = listByIds(idList);
        log.info("DicServiceImpl dicList {}", dics);
        return dics;
    }

}