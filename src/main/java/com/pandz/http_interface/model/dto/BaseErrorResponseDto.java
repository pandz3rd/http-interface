package com.pandz.http_interface.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseErrorResponseDto {
    private String responseCode;
    private String responseMessage;
}
