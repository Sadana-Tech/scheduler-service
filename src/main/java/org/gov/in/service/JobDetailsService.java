package org.gov.in.service;


import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;

public interface JobDetailsService {

    public org.gov.in.entity.JobDetails save(JobDetails jobDetails);

    public Response fetchByName(String jobName);

     Response fetchById(int id);

    public Response fetchAll();

    public Response fetchByStatus(String status);
}
