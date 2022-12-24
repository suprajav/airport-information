package com.app.airport.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for common errors
 */
@AllArgsConstructor
@Getter
public enum CommonErrorTypeEnum {

    DEFAULT_ERROR("Something went wrong", 1000),
    BAD_REQUEST("The request is invalid, violation of constraint!", 1001);

    private final String message;
    private final int errorCode;
}
