package org.gov.in.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.gov.in.constants.JobStatus;
import org.gov.in.constants.StatusEnum;
import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;
import org.gov.in.repository.JobDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobDetailsServiceImpl implements JobDetailsService {

  private JobDetailsRepository jobDetailsRepository;


  @Override
  public Response save(JobDetails jobDetails) {
    BeanUtils.copyProperties(jobDetailsRepository.save(
        org.gov.in.entity.JobDetails.builder().jobName(jobDetails.getJobName())
            .status(JobStatus.IN_PROGRESS.name()).build()), jobDetails);
    return Response.builder().statusCode(StatusEnum.JOB_DETAILS_SAVED_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_DETAILS_SAVED_SUCCESS.getStatusMsg())
        .jobDetails(jobDetails).build();
  }

  @Override
  public Response fetchByName(String jobName) {
    JobDetails jobDetails = JobDetails.builder().build();
    BeanUtils.copyProperties(jobDetailsRepository.findByJobName(jobName), jobDetails);
    return Response.builder().statusCode(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusMsg())
        .jobDetails(jobDetails).build();

  }

  @Override
  public Response fetchById(int id) {
    JobDetails jobDetails = JobDetails.builder().build();
    BeanUtils.copyProperties(jobDetailsRepository.findById(id), jobDetails);
    return Response.builder().statusCode(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusMsg())
        .jobDetails(jobDetails).build();

  }

  @Override
  public Response fetchByStatus(String status) {
    List<JobDetails> jobDetailsList = new ArrayList<>();
    jobDetailsRepository.findByStatus(status).forEach(jobDetails -> {
      JobDetails jobDetailsModel = JobDetails.builder().build();
      BeanUtils.copyProperties(jobDetails, jobDetailsModel);
      jobDetailsList.add(jobDetailsModel);
    });

    return Response.builder().statusCode(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusMsg())
        .jobDetailsList(jobDetailsList).build();

  }

  @Override
  public Response fetchAll() {
    List<JobDetails> jobDetailsList = new ArrayList<>();
    jobDetailsRepository.findAll().forEach(jobDetails -> {
      JobDetails jobDetailsModel = JobDetails.builder().build();
      BeanUtils.copyProperties(jobDetails, jobDetailsModel);
      jobDetailsList.add(jobDetailsModel);
    });

    return Response.builder().statusCode(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusCode())
        .statusMsg(StatusEnum.JOB_DETAILS_FETCHED_SUCCESS.getStatusMsg())
        .jobDetailsList(jobDetailsList).build();

  }
}
