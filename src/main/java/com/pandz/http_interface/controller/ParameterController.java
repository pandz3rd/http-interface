package com.pandz.http_interface.controller;

import com.pandz.http_interface.model.dto.GetParameterRequest;
import com.pandz.http_interface.service.ParameterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/parameter")
public class ParameterController {
    private final ParameterService parameterService;

    @GetMapping("/refresh-cache")
    public HttpStatus refreshCache() {
        return parameterService.refreshCache();
    }

    @PostMapping("/value")
    public String getConfigValue(@RequestBody GetParameterRequest request) {
        return parameterService.getValueParameter(request.getParamName());
    }
}
