package com.xuegao.springboot_tool.spring.importselector.printcase.print;

/**
 * Created by @author yihui in 18:55 19/12/13.
 */
public class DbPrint implements IPrint {
    @Override
    public void print() {
        System.out.println("db print");
    }
}
