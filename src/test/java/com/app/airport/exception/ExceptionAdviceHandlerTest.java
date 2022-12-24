package com.app.airport.exception;

import com.app.airport.enums.CommonErrorTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionAdviceHandlerTest {

    private ExceptionAdviceHandler sut;

    @BeforeEach
    public void init() {
        sut = new ExceptionAdviceHandler();
    }

    @Test
    void testHandleGenericException() {
        var responseEntity = sut.handleException(new Exception(""));
        assertEquals(CommonErrorTypeEnum.DEFAULT_ERROR.getMessage(), Objects.requireNonNull(responseEntity.getBody()).getErrorText());
        assertEquals(CommonErrorTypeEnum.DEFAULT_ERROR.getErrorCode(), responseEntity.getBody().getErrorCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testHandleConstraintViolationException() {
        var responseEntity = sut.handleConstraintViolationException(new Exception("The request is invalid, violation of constraint!"));
        assertEquals("The request is invalid, violation of constraint!", responseEntity.getBody().getErrorText());
        assertEquals(CommonErrorTypeEnum.BAD_REQUEST.getErrorCode(), responseEntity.getBody().getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}