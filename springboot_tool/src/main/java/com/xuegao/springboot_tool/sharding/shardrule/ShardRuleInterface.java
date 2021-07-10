package com.xuegao.springboot_tool.sharding.shardrule;

public interface ShardRuleInterface {
    String getTableByRule(String var1, Integer var2);

    String getDatabaseByRule(String var1, Integer var2);
}