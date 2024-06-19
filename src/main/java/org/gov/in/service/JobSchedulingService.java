package org.gov.in.service;

import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;

public interface JobSchedulingService {

  public Response scheduleATask(JobDetails job, Runnable tasklet);

  public Response removeScheduledTask(JobDetails job);

}
