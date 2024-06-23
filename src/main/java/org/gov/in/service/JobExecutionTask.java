package org.gov.in.service;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.gov.in.constants.JobStatus;
import org.gov.in.model.JobDetails;
import org.gov.in.repository.JobDetailsRepository;
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
    private JobDetailsRepository jobDetailsRepository;

    public JobExecutionTask(WebClient webClient, JobDetailsRepository jobDetailsRepository) {
        this.webClient = webClient;
        this.jobDetailsRepository = jobDetailsRepository;
    }

    @Override
    public void run() {

        log.info("Started executing job ---", job.getJobName());
        ResponseEntity<String> response;
        if (job.getMethodType().equalsIgnoreCase(HttpMethod.POST.name())) {
            response = webClient.post().uri(job.getBaseURL() + job.getApiURL())
                    .header("Content-Type", "application/json").bodyValue(job.getJsonBody()).retrieve()
                    .toEntity(String.class)
                    .onErrorComplete().
                    block();
        } else {
            response = webClient.get().uri(job.getBaseURL() + job.getApiURL())
                    .header("Content-Type", "application/json").retrieve().toEntity(String.class).block();
        }

        org.gov.in.entity.JobDetails jobDetailsDB = jobDetailsRepository.findById(job.getJobId()).get();

        if (response==null || response.getStatusCode().isError()) {
            jobDetailsDB.setStatus(JobStatus.FAILED.name());
            log.error("Failure calling Job:" + job.getJobName() + "with URL:" + job.getBaseURL()
                    + job.getApiURL());
        } else {
            jobDetailsDB.setStatus(JobStatus.COMPLETED.name());
            log.info(response.getBody());

        }
        jobDetailsDB.setEndDt(LocalDateTime.now());
        jobDetailsRepository.save(jobDetailsDB);
    }
}
