package org.gov.in.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Response {

    private int statusCode;
    private String statusMsg;
    private ErrorDetails errorDetails;

    private JobDetails jobDetails;

    private List<JobDetails> jobDetailsList;


}
