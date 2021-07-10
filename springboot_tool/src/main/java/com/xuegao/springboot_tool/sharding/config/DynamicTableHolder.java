package com.xuegao.springboot_tool.sharding.config;

public class DynamicTableHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal();

    public static String getTable() {
        return (String)CONTEXT_HOLDER.get();
    }

    public static void clearTable() {
        CONTEXT_HOLDER.remove();
    }

    public static void putTable(String table) {
        CONTEXT_HOLDER.set(table);
    }

    private DynamicTableHolder() {
    }
}
