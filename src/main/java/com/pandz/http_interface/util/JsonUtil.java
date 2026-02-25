package com.pandz.http_interface.util;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
public class JsonUtil {
    private JsonUtil() {}

    public static String objectAsStringJson(Object data) {
        return (new ObjectMapper()).writeValueAsString(data);
    }
}
