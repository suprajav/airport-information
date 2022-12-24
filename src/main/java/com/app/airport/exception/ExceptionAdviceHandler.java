package com.app.airport.exception;

import com.app.airport.dto.ErrorDTO;
import com.app.airport.enums.CommonErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Advice for exception handling
 */
@ControllerAdvice
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExceptionAdviceHandler {

    /**
     * constraintViolationException handler
     * @param ex The exception that has occurred
     * @return Error Response
     */
    @ExceptionHandler({ConstraintViolationException.class, MissingServletRequestParameterException.class})
    public final ResponseEntity<ErrorDTO> handleConstraintViolationException(Exception ex) {
        log.error("ConstraintViolationException", ex);
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage(), CommonErrorTypeEnum.BAD_REQUEST.getErrorCode()), HttpStatus.BAD_REQUEST);
    }

    /**
     * handles general exception
     * @param ex The exception that has occurred
     * @return Error Response
     */
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("GenericException", ex);
        return new ResponseEntity<>(new ErrorDTO(CommonErrorTypeEnum.DEFAULT_ERROR.getMessage(),
                CommonErrorTypeEnum.DEFAULT_ERROR.getErrorCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
