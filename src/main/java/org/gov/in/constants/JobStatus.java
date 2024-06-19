package org.gov.in.constants;


import lombok.Getter;

@Getter
public enum JobStatus {
  IN_PROGRESS, COMPLETED, INTERRUPTED;
  private String status;
}
