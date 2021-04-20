package com.xuegao.xuegaomybatis;

/**
 * <br/> @PackageName：com.xuegao.xuegaomybatis
 * <br/> @ClassName：test
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/19 11:51
 */
public class test {
    public static void main(String[] args) {
        String s = "3.666666";
        String substring = s.substring(0, s.lastIndexOf(".") + 2);
        System.out.println(substring);
    }
}