package com.app.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for validator error or exception scenarios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String errorText;
    private int errorCode;
}
