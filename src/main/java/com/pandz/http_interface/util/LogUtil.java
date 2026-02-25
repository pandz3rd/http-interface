package com.pandz.http_interface.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class LogUtil {
    private LogUtil() {}

    private static final String DOUBLE_LINE = "====================================================================================";
    private static final String REQUEST_LINE = "---------------------------------- REQUEST -----------------------------------------";
    private static final String RESPONSE_LINE = "---------------------------------- RESPONSE ----------------------------------------";

    public static final String LF = "\n";

    public static String logRequestHeader(ContentCachingRequestWrapper request) {
        List<String> list = Collections.list(request.getHeaderNames());
        Map<Object, Object> map = list.stream().collect(Collectors.toMap(
                String::valueOf,
                s -> String.valueOf(s).equals("authorization") ? "*************************" : request.getHeader(String.valueOf(s))));
        return JsonUtil.objectAsStringJson(map);
    }

    public static String logRequest(byte[] body) {
        return new String(body, StandardCharsets.UTF_8);
    }

    public static String logResponse(ContentCachingResponseWrapper response) {
        return new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
    }

    public static String logResponseHeader(ContentCachingResponseWrapper response) {
        List<String> list = new ArrayList<>(response.getHeaderNames());

        Map<Object, Object> map = list.stream()
                .collect(Collectors.toMap(
                        String::valueOf,
                        s -> s.equalsIgnoreCase("authorization") ? "*************************" : response.getHeader(s),
                        (v1, v2) -> v1    // keep the first value if duplicate
                ));
        return JsonUtil.objectAsStringJson(map);
    }

    public static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        return request instanceof ContentCachingRequestWrapper ? (ContentCachingRequestWrapper) request : new ContentCachingRequestWrapper(request, 0);
    }

    public static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        return response instanceof ContentCachingResponseWrapper ? (ContentCachingResponseWrapper) response : new ContentCachingResponseWrapper(response);
    }

    public static void logging(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper,
                               String payloadRequest, String payloadResponse, long duration) {
        StringBuilder sb = new StringBuilder(LF);
        sb.append(DOUBLE_LINE).append(LF);
        sb.append(String.format("URI : {%s}", requestWrapper.getRequestURI())).append(LF);
        sb.append(REQUEST_LINE).append(LF);
        sb.append(String.format("Request Header : {%s}", logRequestHeader(requestWrapper))).append(LF);
        sb.append(String.format("Request Body : {%s}", payloadRequest)).append(LF);
        sb.append(RESPONSE_LINE).append(LF);
        sb.append(String.format("Response Header : {%s}", logResponseHeader(responseWrapper))).append(LF);
        sb.append(String.format("Response Body : {%s}", payloadResponse)).append(LF);
        sb.append(String.format("Duration: {%s}", duration + "ms")).append(LF);
        sb.append(DOUBLE_LINE);
        log.info(sb.toString());
    }

    public static void logging(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper,
                               String payloadRequest, String payloadResponse) {
        StringBuilder sb = new StringBuilder(LF);
        sb.append(DOUBLE_LINE).append(LF);
        sb.append(String.format("URI : {%s}", requestWrapper.getRequestURI())).append(LF);
        sb.append(REQUEST_LINE).append(LF);
        sb.append(String.format("Request Header : {%s}", logRequestHeader(requestWrapper))).append(LF);
        sb.append(String.format("Request Body : {%s}", payloadRequest)).append(LF);
        sb.append(RESPONSE_LINE).append(LF);
        sb.append(String.format("Response Header : {%s}", logResponseHeader(responseWrapper))).append(LF);
        sb.append(String.format("Response Body : {%s}", payloadResponse)).append(LF);
        sb.append(DOUBLE_LINE);
        log.info(sb.toString());
    }
}
