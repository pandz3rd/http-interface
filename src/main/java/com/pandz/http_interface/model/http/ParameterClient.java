package com.pandz.http_interface.model.http;

import com.pandz.http_interface.model.dto.GetParameterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/parameter")
public interface ParameterClient {
    @GetExchange("/refresh-cache")
    ResponseEntity<Void> refreshCache();

    @PostExchange("/value")
    String getValue(@RequestBody GetParameterRequest request);
}
