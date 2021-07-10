// package com.xuegao.springboot_tool.sharding.config;
//
// public class DynamicDataSourceHolder {
//     private static String USEFUL_DB = null;
//     private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal();
//
//     public DynamicDataSourceHolder() {
//     }
//
//     public static void putDataSource(String name) {
//         THREAD_LOCAL.set(name);
//     }
//
//     public static String getDataSource() {
//         return null != USEFUL_DB ? USEFUL_DB : (String)THREAD_LOCAL.get();
//     }
//
//     public static void removeDataSource() {
//         THREAD_LOCAL.remove();
//     }
// }
