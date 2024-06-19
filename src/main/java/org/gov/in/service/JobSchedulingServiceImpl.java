package org.gov.in.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.gov.in.constants.StatusEnum;
import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobSchedulingServiceImpl implements JobSchedulingService {

  Map<String, ScheduledFuture<?>> jobMap = new HashMap<>();
  private TaskScheduler taskScheduler;

  public JobSchedulingServiceImpl(TaskScheduler taskScheduler) {
    this.taskScheduler = taskScheduler;

  }

  @Override
  public Response scheduleATask(JobDetails job, Runnable tasklet) {
    log.info("Scheduling task with job id: " + job.getJobName() + " and cron expression: "
        + job.getCron());
    ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet,
        new CronTrigger(job.getCron(), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
    jobMap.put(job.getJobName(), scheduledTask);
    return Response.builder().statusCode(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusMsg()).build();

  }

  @Override
  public Response removeScheduledTask(JobDetails job) {
    log.info("Removing scheduled job : " + job.getJobName());
    ScheduledFuture<?> scheduledTask = jobMap.get(job.getJobName());
    if (scheduledTask != null) {
      scheduledTask.cancel(job.isInterrupt());
      jobMap.put(job.getJobName(), null);
    }
    return Response.builder().statusCode(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusMsg()).build();
  }

}
