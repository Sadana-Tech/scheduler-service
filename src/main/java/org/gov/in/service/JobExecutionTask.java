package org.gov.in.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.gov.in.model.JobDetails;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j

public class JobExecutionTask implements Runnable {

  @Setter
  @Getter
  private JobDetails job;

  private WebClient webClient;

  public JobExecutionTask(WebClient webClient) {
    this.webClient = webClient;

  }


  @Override
  public void run() {

    log.info("Started executing job ---", job.getJobName());
    ResponseEntity<String> response;
    if (job.getMethodType().equalsIgnoreCase(HttpMethod.POST.name())) {
      response = webClient.post().uri(job.getBaseURL() + job.getApiURL())
          .header("Content-Type", "application/json").bodyValue(job.getJsonBody()).retrieve()
          .toEntity(String.class).block();
    } else {
      response = webClient.get().uri(job.getBaseURL() + job.getApiURL())
          .header("Content-Type", "application/json").retrieve().toEntity(String.class).block();
    }

    if (response.getStatusCode().isError()) {
      log.error("Failure calling Job:" + job.getJobName() + "with URL:" + job.getBaseURL()
          + job.getApiURL());
    } else {
      log.info(response.getBody());

    }
  }
}
