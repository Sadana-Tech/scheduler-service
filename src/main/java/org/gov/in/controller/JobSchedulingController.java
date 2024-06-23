package org.gov.in.controller;

import lombok.AllArgsConstructor;
import org.gov.in.model.JobDetails;
import org.gov.in.model.Response;
import org.gov.in.service.JobDetailsService;
import org.gov.in.service.JobExecutionTask;
import org.gov.in.service.JobSchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule")
@AllArgsConstructor
public class JobSchedulingController {
    private JobSchedulingService jobSchedulingService;
    private JobExecutionTask taskDefinitionBean;
    private JobDetailsService jobDetailsService;

    @PostMapping(path = "/job", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> scheduleATask(@RequestBody JobDetails jobDetails) {
        taskDefinitionBean.setJob(jobDetails);
        return new ResponseEntity<>(jobSchedulingService.scheduleATask(jobDetails, taskDefinitionBean), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/remove", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response> removeJob(@RequestBody JobDetails jobDetails) {
        return new ResponseEntity<>(jobSchedulingService.removeScheduledTask(jobDetails), HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "/{jobId}")
    public ResponseEntity<Response> fetchJobDetail(@PathVariable int jobId) {
        return new ResponseEntity<>(jobDetailsService.fetchById(jobId), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{jobName}")
    public ResponseEntity<Response> fetchJobDetailByName(@PathVariable String jobName) {
        return new ResponseEntity<>(jobDetailsService.fetchByName(jobName), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{status}")
    public ResponseEntity<Response> fetchJobDetailByStatus(@PathVariable String status) {
        return new ResponseEntity<>(jobDetailsService.fetchByStatus(status), HttpStatus.ACCEPTED);
    }

}
