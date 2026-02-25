package com.pandz.http_interface.service;

import com.pandz.http_interface.model.dto.GetParameterRequest;
import com.pandz.http_interface.model.http.ParameterClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParameterService {
    private final ParameterClient parameterClient;

    public HttpStatus refreshCache() {
        ResponseEntity<Void> response = parameterClient.refreshCache();
        HttpStatusCode statusCode = response.getStatusCode();
        return HttpStatus.valueOf(statusCode.value());
    }

    public String getValueParameter(String paramName) {
        return parameterClient.getValue(GetParameterRequest.builder().paramName(paramName).build());
    }
}
