package com.pandz.http_interface.exception;

import com.pandz.http_interface.model.dto.BaseErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exception(Exception e) {
        log.error("Exception is UNCAUGHT, details : ", e);
        BaseErrorResponseDto errorDetail = BaseErrorResponseDto.builder()
                .responseCode("99")
                .responseMessage("Error adalah warna dalam kehidupan")
                .build();
        return new ResponseEntity<>(errorDetail, HttpStatus.OK);
    }
}
