/*
 * 软件版权: 恒生电子股份有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/3  wanggk25832  新增
 * ========    =======  ============================================
 */
package com.hundsun.accountingsystem.THg.Quartz;

/**
 * 功能说明:
 *
 * @author wanggk25832
 */
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 配置任务
 */
@Configuration
public class QuartzConfiguration {

  /**
   *  配置任务
   * @param quartzTask QuartzTask为需要执行的任务
   * @return
   */
  @Bean(name = "DQJob")
  public MethodInvokingJobDetailFactoryBean detailFactoryBean(QuartzTask quartzTask) {

    MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

    // 是否并发执行
    jobDetail.setConcurrent(false);

    // 设置任务的名字
    jobDetail.setName("DQJob");

    // 设置任务的分组，在多任务的时候使用
    jobDetail.setGroup("DQJobGroup");

    // 需要执行的对象
    jobDetail.setTargetObject(quartzTask);

    /*
     * TODO  非常重要
     * 执行QuartzTask类中的需要执行方法
     */
    jobDetail.setTargetMethod("DQ");
    return jobDetail;
  }

  /**
   * 定时触发器
   * @param DQJob 任务
   * @return
   */
  @Bean(name = "jobTrigger")
  public CronTriggerFactoryBean cronJobTrigger(JobDetail DQJob){

    CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();

    tigger.setJobDetail(DQJob);

    //cron表达式，每1分钟执行一次
    tigger.setCronExpression("0 0 10 * * ?");
    tigger.setName("DQJobTrigger");
    return tigger;
  }

  /**
   * 调度工厂
   * @param jobTrigger 触发器
   * @return
   */
  @Bean(name = "scheduler")
  public SchedulerFactoryBean schedulerFactory(Trigger jobTrigger) {

    SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

    // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
    factoryBean.setOverwriteExistingJobs(true);

    // 延时启动，应用启动1秒后
    factoryBean.setStartupDelay(1);

    // 注册触发器
    factoryBean.setTriggers(jobTrigger);
    return factoryBean;
  }

}
