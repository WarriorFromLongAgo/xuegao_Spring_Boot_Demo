package com.xuegao.springboot_tool;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.bootinit
 * <br/> @ClassName：BootInit
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/4/1 23:40
 */
@Service
public class BootInit {

    @PostConstruct
    public void init() {
        // String sql = "replace into money (id, name, money) values (120, '初始化', 200)," +
        //         "(130, '初始化', 200)," +
        //         "(140, '初始化', 200)," +
        //         "(150, '初始化', 200)";
        // jdbcTemplate.execute(sql);
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
        System.out.println("1111111111111111111111111111111111111111111111111111111");
    }

}