/*
package com.benbird.bencenter.task;

import com.benbird.bencenter.enums.JobConfigStatusEnum;
import com.benbird.bencenter.enums.JobTypeEnum;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.models.DO.ElasticJobConfigDO;
import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

*/
/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述: ElasticJob初始化（Java方式）
 * @author Admin
 *//*

@Slf4j
@Component
public class ElasticJobInit {


    */
/**
     * Spring容器
     *//*

    @Autowired
    private ApplicationContext applicationContext;


    */
/**
     * Job ZK配置中心
     *//*

    @Autowired
    @Qualifier("benbirdRegistryCenter")
    private CoordinatorRegistryCenter coordinatorRegistryCenter;

    */
/**
     * 系统初始化，初始化elastic-Job
     *//*

    @PostConstruct
    public void loadJobConfig() {

        log.info("ElasticJonConfig Loading");

        ElasticJobConfigDO elasticJobConfigDO = new ElasticJobConfigDO();
        elasticJobConfigDO.setStatus(JobConfigStatusEnum.NORMAL.getCode());
        List<ElasticJobConfigDO> elasticJobConfigDOList = Lists.newArrayList(
                new ElasticJobConfigDO()
                        .setJobNo("11")
                        .setJobName("测试任务")
                        .setJobCronExpress("0 /1 * * * ? ").setJobCronExpressDesc("一分钟执行一次")
                        .setJobClass("testSimpleJob").setJobDesc("测试任务")
                        .setShardTotalCount(1).setJobType("SIMPLE").setStatus("NORMAL")
        );
        if (CollectionUtils.isEmpty(elasticJobConfigDOList)) {
            log.info("Job配置信息为空。");
            return;
        }
        try {
            //获取applicationContext所有的Bean，查找Bean是否存在，存在就加载
            String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beanDefinitionNames);
            for (ElasticJobConfigDO aElasticJobConfigDO : elasticJobConfigDOList) {
                log.info("类在这:{}", aElasticJobConfigDO.getJobClass());
                if (Arrays.binarySearch(beanDefinitionNames, aElasticJobConfigDO.getJobClass()) < 0) {
                    log.error("{}不存在ApplicationContent中", aElasticJobConfigDO.getJobClass());
                    continue;
                }
                initJobToRegistry(aElasticJobConfigDO);
            }
        } catch (Exception e) {
            log.error("Elastic-job初始化错误", e);
        }

    }


    */
/**
     * 向ZK注册的Elastic-Job配置
     *
     * @param elasticJobConfigDO Job配置
     *//*

    public void initJobToRegistry(ElasticJobConfigDO elasticJobConfigDO) {

        try {
            log.info("初始化Job，向ZK注册Job信息 Job配置信息: {}", elasticJobConfigDO);
            if (!CronExpression.isValidExpression(elasticJobConfigDO.getJobCronExpress())) {
                log.error("Cron表达式有误,{}", elasticJobConfigDO.getJobCronExpress());
                return;
            }
            String jobClass = elasticJobConfigDO.getJobClass();
            //获取对应Job
            ElasticJob elasticJob = (ElasticJob) applicationContext.getBean(jobClass);
            Class clazz = elasticJob.getClass();
            // 定义作业核心配置
            JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration
                    .newBuilder(elasticJobConfigDO.getJobName(), elasticJobConfigDO.getJobCronExpress(), elasticJobConfigDO.getShardTotalCount())
                    .failover(Boolean.parseBoolean(elasticJobConfigDO.getFailover()))
                    .misfire(Boolean.FALSE)
                    .jobParameter(elasticJobConfigDO.getJobParameter())
                    .description(elasticJobConfigDO.getJobDesc())
                    .build();
            String jobType = elasticJobConfigDO.getJobType();
            // 通过数据库状态判断定时任务是否执行
            boolean disabled = true;
            if (JobConfigStatusEnum.NORMAL.getCode().equals(elasticJobConfigDO.getStatus())) {
                disabled = false;
            }
            if (JobTypeEnum.SIMPLE.getCode().equals(jobType)) {
                // 定义SIMPLE类型配置
                SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(jobCoreConfiguration,
                        clazz.getCanonicalName());
                new SpringJobScheduler(elasticJob, coordinatorRegistryCenter, LiteJobConfiguration.newBuilder(simpleJobConfig)
                        .monitorExecution(Boolean.parseBoolean(elasticJobConfigDO.getMonitorExecution()))
                        .overwrite(true)
                        .disabled(disabled)
                        .build())
                        .init();
            } else if (JobTypeEnum.DATA_FLOW.getCode().equals(jobType)) {
                // 定义DATA_FLOW类型配置
                DataflowJobConfiguration dataFlowJobConfig = new DataflowJobConfiguration(jobCoreConfiguration,
                        clazz.getCanonicalName(), true);
                new SpringJobScheduler(elasticJob, coordinatorRegistryCenter, LiteJobConfiguration.newBuilder(dataFlowJobConfig)
                        .monitorExecution(Boolean.parseBoolean(elasticJobConfigDO.getMonitorExecution()))
                        .overwrite(true)
                        .disabled(disabled)
                        .build())
                        .init();
            }
            log.info("初始化Job，向ZK注册Job信息 类名: {}", clazz.getCanonicalName());
        } catch (Exception e) {
            throw new BenbirdException(BenbirdErrorCode.JOB_CONFIG_ERROR, "初始化ElasticJob错误", e);
        }
    }

}
*/
