package org.gov.in.entity;

import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Table
@Builder
@Data
public class JobDetails {

  private int id;
  private String jobName;
  private LocalDateTime startDt;
  private LocalDateTime endDt;
  private String status;
}
