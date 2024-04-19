package com.s3.fileHandler.exception;

import com.s3.fileHandler.constant.Messages;
import com.s3.fileHandler.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({FailedToParseException.class})
    public ApiResponse handleFailedToParseException(FailedToParseException exception) {
        return ApiResponse.builder()
                .message(Messages.FAILED_TO_PARSE_FILE)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build();
    }

    @ExceptionHandler({RecordNotFoundException.class})
    public ApiResponse handleRecordNotFoundExption(RecordNotFoundException exception) {
        log.error("In RecordNotFoundExption handler");
        return ApiResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

}
