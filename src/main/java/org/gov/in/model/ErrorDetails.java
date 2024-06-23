package org.gov.in.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {

    private int errorCode;
    private String errorMsg;

}
