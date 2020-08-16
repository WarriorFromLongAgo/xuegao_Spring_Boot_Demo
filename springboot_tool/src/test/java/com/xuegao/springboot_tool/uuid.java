package com.xuegao.springboot_tool;

import java.util.UUID;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool
 * <br/> @ClassName：uuid
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/7/16 17:37
 */
public class uuid {

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        String s1 = HungrySingleton.getInstance().random2();
        System.out.println(s1);
        s1 = HungrySingleton.getInstance().random2();
        System.out.println(s1);
        s1 = HungrySingleton.getInstance().random2();
        System.out.println(s1);
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        String s2 = HungrySingleton.random1();
        System.out.println(s2);
        s2 = HungrySingleton.random1();
        System.out.println(s2);
        s2 = HungrySingleton.random1();
        System.out.println(s2);
    }


}