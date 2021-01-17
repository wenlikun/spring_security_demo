/*
package com.benbird.bencenter.task;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述: ElasticJob ZK配置中心
 *//*

@Slf4j
@Configuration
public class JobConfiguration {

    @Value("${elasticjob.zookeeper.address}")
    private String regZookeeper;

    @Value("${elasticjob.namespace}")
    private String namespace;

    */
/**
     * ZK调度注册中心（CoordinatorRegistryCenter）初始化
     *
     * @return regCenter实例
     *//*

    @Bean(name = "benbirdRegistryCenter")
    public CoordinatorRegistryCenter registryCenterInit() {

        log.info("regZK address: {}", regZookeeper);
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(regZookeeper, namespace);
        log.info("init regCenter begin");
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        regCenter.init();
        log.info("init regCenter end");
        return regCenter;
    }


}
*/
