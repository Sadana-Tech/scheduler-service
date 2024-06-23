package org.gov.in.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    JOB_SCHEDULE_ERROR(701, "Exception occurred while executing Job."),
    JOB_SCHEDULE_SUCCESS(600, "Job execution process set successfully."),
    JOB_DETAILS_SAVED_SUCCESS(601, "Job details saved successfully."),
    JOB_DETAILS_FETCHED_SUCCESS(601, "Job details fetched successfully.");
    private Integer statusCode;
    private String statusMsg;

}
