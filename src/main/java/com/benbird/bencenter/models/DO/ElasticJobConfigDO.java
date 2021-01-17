package com.benbird.bencenter.models.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.benbird.bencenter.models.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * JOB配置信息
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("elastic_job_config")
public class ElasticJobConfigDO extends BaseDO {

    /**
     * JOB编号
     */
    private String jobNo;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务运行时间表达式
     */
    private String jobCronExpress;

    /**
     * 任务运行时间表达式描述
     */
    private String jobCronExpressDesc;

    /**
     * 类名或者bean名
     */
    private String jobClass;

    /**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 任务分片总数
     */
    private Integer shardTotalCount;

    /**
     * 是否开启任务执行失效转移
     * 开启:true 关闭:false（缺省值）
     */
    @TableField(exist = false)
    private String failover;

    /**
     * 监视器
     * 开启:true（缺省值） 关闭:false
     */
    @TableField(exist = false)
    private String monitorExecution;

    /**
     * 任务类型（SIMPLE 、DATA_FLOW、Script）
     */
    private String jobType;

    /**
     * 任务类型（NORMAL 、PAUSED）
     */
    private String status;

    /**
     * 任务参数（暂时用来存放Job对应的Handler执行片数）
     */
    private String jobParameter;
}
