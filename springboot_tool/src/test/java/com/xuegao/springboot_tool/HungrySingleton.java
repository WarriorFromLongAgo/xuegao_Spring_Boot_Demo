package com.xuegao.springboot_tool;

import java.util.UUID;

public class HungrySingleton {
    private HungrySingleton() {
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static String random1() {
        return " random1 = " + UUID.randomUUID().toString();
    }

    public String random2() {
        return " random2 = " + UUID.randomUUID().toString();
    }
}