package com.work.generaldb.shardingjdbc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.zaxxer.hikari.HikariDataSource;

import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;

@ConfigurationProperties(prefix = "sharding.jdbc")//表示读取本底配置文件 前缀sharding.jdbc
public class ShardingMasterSlaveConfig {

    // 存放本地多个数据源   最终放在map集合中   key为yml配置的 ds_slave_0
    private Map<String, HikariDataSource> dataSources = new HashMap<>();

    private MasterSlaveRuleConfiguration masterSlaveRule;

    public Map<String, HikariDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, HikariDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public MasterSlaveRuleConfiguration getMasterSlaveRule() {
        return masterSlaveRule;
    }

    public void setMasterSlaveRule(MasterSlaveRuleConfiguration masterSlaveRule) {
        this.masterSlaveRule = masterSlaveRule;
    }
}