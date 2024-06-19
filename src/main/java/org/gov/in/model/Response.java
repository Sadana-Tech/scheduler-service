package org.gov.in.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

  private int statusCode;
  private String statusMsg;
  private ErrorDetails errorDetails;

  private JobDetails jobDetails;

  private List<JobDetails> jobDetailsList;


}
