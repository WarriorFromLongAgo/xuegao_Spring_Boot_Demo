package com.xuegao.springboot_tool.service.impl;

import com.xuegao.springboot_tool.dao.IMyJvmMapper;
import com.xuegao.springboot_tool.model.doo.MyJvm;
import com.xuegao.springboot_tool.service.interfaces.IMyJvmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
 * <br/> @ClassName：MyJvmServiceImpl
 * <br/> @Description：
 * <br/> @author：花名 xuegao
 * <br/> @date：2020/9/9 10:54
 */
@Service
public class MyJvmServiceImpl extends BaseServiceImpl<IMyJvmMapper, MyJvm> implements IMyJvmService {
    private final static Logger log = LoggerFactory.getLogger(MyJvmServiceImpl.class);

    @Override
    public MyJvm getMyJvmById(Long id) {
        return getById(id);
    }

    /**
     * <br/> @Title: CPU拉满
     * <br/> @MethodName:  cpu100
     * <br/> @param id:
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/9/10 10:25
     */
    @Override
    public void cpu100(Long id) {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CPU();
                }
            }).start();
        }
    }

    public void CPU() {
        while (true) {

        }
    }

    /***
     * <br/> @Title: 栈内存溢出 stackOverflowError
     * <br/> @MethodName:
     * <br/> @param null:
     * <br/> @Return
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/9/9 11:29
     */
    int i = 0;

    @Override
    public void stackOverflowError() {
        log.error(" stackOutOf = " + (++i));
        stackOverflowError();
    }

    /**
     * <br/> @Title: 堆内存溢出 outOfMemoryError
     * <br/> @MethodName:  outOfMemoryError
     * <br/>
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: xuegao
     * <br/> @date:  2020/9/9 11:29
     */
    List<String> strList = new ArrayList<>();

    @Override
    public void outOfMemoryError() {
        try {
            while (true) {
                strList.add(new String("xuegao xuegao xuegao "));
                strList.add(new String("xuegao xuegao xuegao "));
                strList.add(new String("xuegao xuegao xuegao "));
            }
        } catch (Exception e) {
            // java.lang.OutOfMemoryError: Java heap space
            e.printStackTrace();
        }
    }
}