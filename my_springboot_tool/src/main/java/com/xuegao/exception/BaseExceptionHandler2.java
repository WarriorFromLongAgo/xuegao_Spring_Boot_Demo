package com.xuegao.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.fff.springbootapiseedtest.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.exception
 * <br/> @ClassName：BaseExceptionHandler2
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/4/5 15:19
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler2 {

    /**修改统一异常处理器，将异常方法中的直接打印改为日志输入并打印
     * -------- 通用异常处理方法 --------
     * 日志的环境即spring.profiles.acticve，跟随项目启动；
     *
     * 启动后，即可到自定目录查找到生成的日志文件；
     *
     * 本地idea调试时，推荐Grep Console插件可实现控制台的自定义颜色输出
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        // e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        // return R.error();
        return null;
    }

}