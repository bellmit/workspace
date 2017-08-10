package com.jumore.jmbi.common.util;

import com.dangdang.ddframe.job.internal.schedule.JobRegistry;
import com.dangdang.ddframe.job.internal.schedule.JobScheduleController;

/**
 * 定时任务作业
 */
public class JobUtil {

    /** 
     * 功能说明：重新设置定时任务执行时间
     * 
     * @param jobName
     * @param cronExpression 
     * void
     */
    public static void changeCron(String jobName, String cronExpression) {
        JobScheduleController jobScheduleController = JobRegistry.getInstance().getJobScheduleController(jobName);
        jobScheduleController.rescheduleJob(cronExpression);
    }

    /** 
     * 功能说明：获取每{hour}小时执行一次的定时任务表达式
     * 
     * @param hour 
     * void
     */
    public static String getHourlyCron(int hour) {
        return "0 0 0/" + hour + " * * ?";
    }

}
