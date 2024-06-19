package org.gov.in.service;


import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;

public interface JobDetailsService {

  public Response save(JobDetails jobDetails);

  public Response fetchByName(String jobName);

  public Response fetchById(int jobId);

  public Response fetchAll();

  public Response fetchByStatus(String status);
}
