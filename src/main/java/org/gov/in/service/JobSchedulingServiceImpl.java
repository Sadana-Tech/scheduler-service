package org.gov.in.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gov.in.constants.JobStatus;
import org.gov.in.constants.StatusEnum;
import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;
import org.gov.in.repository.JobDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
@AllArgsConstructor
public class JobSchedulingServiceImpl implements JobSchedulingService {

    Map<Integer, ScheduledFuture<?>> jobMap = new HashMap<>();
    private TaskScheduler taskScheduler;
    private JobDetailsService jobDetailsService;
    private JobDetailsRepository jobDetailsRepository;

    @Override
    public Response scheduleATask(JobDetails job, Runnable tasklet) {
        log.info("Scheduling task with job id: " + job.getJobName() + " and cron expression: "
                + job.getCron());
        job.setJobId(jobDetailsService.save(job).getId());
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet,
                new CronTrigger(job.getCron(), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobMap.put(job.getJobId(), scheduledTask);
        job.setStatus(JobStatus.IN_PROGRESS.getStatus());
        return Response.builder().statusCode(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusCode())
                .statusMsg(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusMsg()).jobDetails(job).build();

    }

    @Override
    public Response removeScheduledTask(JobDetails job) {
        log.info("Removing scheduled job : " + job.getJobName());
        ScheduledFuture<?> scheduledTask = jobMap.get(job.getJobId());
        if (scheduledTask != null) {
            scheduledTask.cancel(job.isInterrupt());
            jobMap.remove(job.getJobId());
        }
        org.gov.in.entity.JobDetails jobDetailsDb = jobDetailsRepository.findById(job.getJobId()).get();
        jobDetailsDb.setStatus(JobStatus.INTERRUPTED.name());
        jobDetailsDb =jobDetailsRepository.save(jobDetailsDb);
        BeanUtils.copyProperties(jobDetailsDb,job);

        return Response.builder().statusCode(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusCode())
                .statusMsg(StatusEnum.JOB_SCHEDULE_SUCCESS.getStatusMsg()).jobDetails(job).build();
    }

}
