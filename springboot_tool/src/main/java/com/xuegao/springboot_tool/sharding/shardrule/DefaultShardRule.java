package com.xuegao.springboot_tool.sharding.shardrule;

import org.springframework.stereotype.Component;

@Component("defaultShardRule")
public class DefaultShardRule implements ShardRuleInterface {
    private static final String UNDERLINE = "_";

    public DefaultShardRule() {
    }

    public String getTableByRule(String prefix, Integer shardKey) {
        return prefix + "_" + shardKey % 100;
    }

    public String getDatabaseByRule(String prefix, Integer shardKey) {
        return prefix + "_" + shardKey / 100 % 10;
    }
}
