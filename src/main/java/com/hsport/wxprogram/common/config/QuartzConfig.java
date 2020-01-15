

package com.hsport.wxprogram.common.config;
import com.hsport.wxprogram.common.quartz.FirstJob;
import com.hsport.wxprogram.web.controller.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 路径：com.example.demo.config
 * 类名：
 * 功能：Quartz配置类
 * 备注：
 * 创建人：typ
 * 创建时间：2018/10/10 12:05
 * 修改人：
 * 修改备注：
 * 修改时间：
 */
@Slf4j
@Configuration
public class QuartzConfig {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 方法名：
     * 功能：配置定时任务
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/10 12:06
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    @Bean(name = "firstJobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(FirstJob firstJob){
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         * 是否并发执行
         * 例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         * 如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        //设置定时任务的名字
        jobDetail.setName("srd-demo");
        //设置任务的分组，这些属性都可以在数据库中，在多任务的时候使用
        jobDetail.setGroup("srd");

        //为需要执行的实体类对应的对象
        jobDetail.setTargetObject(firstJob);

        /*
         * sayHello为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
         */
        jobDetail.setTargetMethod("task");
        logger.info("jobDetail 初始化成功！");
        return jobDetail;
    }

    /**
     * 方法名：
     * 功能：配置定时任务的触发器，也就是什么时候触发执行定时任务
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/10 12:14
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        //初始化的cron表达式(每天上午10:15触发)
        trigger.setCronExpression("0 * 11 * * ?");
        //trigger的name
        trigger.setName("srd-demo");
        logger.info("jobTrigger 初始化成功！");
        return trigger;
    }

    /**
     * 方法名：
     * 功能：定义quartz调度工厂
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/10 14:06
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //用于quartz集群，QuartzScheduler启动时更新已存在的job
        factoryBean.setOverwriteExistingJobs(true);
        //延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);
        //注册触发器
        factoryBean.setTriggers(trigger);
        logger.info("scheduler 初始化成功！");
        return factoryBean;
    }
}