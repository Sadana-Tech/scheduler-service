package org.gov.in.exception;

import lombok.extern.slf4j.Slf4j;
import org.gov.in.constants.StatusEnum;
import org.gov.in.model.ErrorDetails;
import org.gov.in.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(JobSchedulerException.class)
    public ResponseEntity<Response> handleFirebaseNotificationException(JobSchedulerException e) {
        log.error("Error occured :  " + e.getMessage());
        return new ResponseEntity<>(
                Response.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .statusMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .errorDetails(ErrorDetails.builder().errorCode(StatusEnum.JOB_SCHEDULE_ERROR.getStatusCode())
                                .errorMsg(e.getMessage()).build())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleHttpResponseException(Exception e) {
        log.error("Error occured :  " + e.getMessage());
        return new ResponseEntity<>(Response.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorDetails(ErrorDetails.builder().errorCode(StatusEnum.JOB_SCHEDULE_ERROR.getStatusCode())
                        .errorMsg(e.getMessage()).build())

                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
