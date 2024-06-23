package org.gov.in.constants;


import lombok.Getter;

@Getter
public enum JobStatus {
    IN_PROGRESS, COMPLETED, INTERRUPTED,FAILED;
    private String status;
}
