package org.gov.in.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobDetails {

  private String jobName;
  private int jobId;
  private String apiURL;
  private String baseURL;
  private String methodType;
  private String cron;
  private String jsonBody;
  private boolean interrupt;
  private String status;
  private LocalDateTime startDt;
  private LocalDateTime endDt;

}
