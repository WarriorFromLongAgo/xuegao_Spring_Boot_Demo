package com.xuegao.utils.auto_increment.test;

import com.fff.auto_increment.utils.IdGenUtils;

/**
 * <br/> @PackageName：com.fff.auto_increment
 * <br/> @ClassName：IdGenUtil_Test
 * <br/> @Description：
 * <br/> @author：feijm
 * <br/> @date：2020/4/4 0:07
 */
public class IdGenUtil_Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            long l = IdGenUtils.genId();
            System.out.println(l);
        }
        // 1340594576165952
        // 1340594576165984
        // 1340594576166016
        // 2147483647
        // 9223372036854775807
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        // 2147483647
        // 9223372036854775807
    }
}