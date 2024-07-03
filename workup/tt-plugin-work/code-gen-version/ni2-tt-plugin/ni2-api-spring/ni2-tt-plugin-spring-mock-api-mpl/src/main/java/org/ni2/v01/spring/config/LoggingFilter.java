package org.ni2.v01.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

// alternative to CommonsRequestLoggingFilter this logs requests and responses
// see https://masterrahul.medium.com/spring-boot-2-request-and-response-logging-778072ce6a7b

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String X_BPI_CORRELATION_ID = "x-bpi-correlation_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
        }
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain)
                    throws ServletException, IOException {
        try {
            if (MDC.get(X_BPI_CORRELATION_ID) == null) {
                MDC.put(X_BPI_CORRELATION_ID, UUID.randomUUID().toString());
            }
            request.getInputStream();

            beforeRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            afterRequest(request, response);
            response.copyBodyToResponse();
            MDC.remove(X_BPI_CORRELATION_ID);
        }
    }

    protected void beforeRequest(ContentCachingRequestWrapper request) {
        LOGGER.info("Server has received a request on thread {}", Thread.currentThread());
        logRequestHeader(request);
    }

    protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        LOGGER.info("Server responded with a response on thread {}", Thread.currentThread());
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            LOGGER.info("Request payload: [{}]", new String(request.getContentAsByteArray(), StandardCharsets.UTF_8));
        }

        logResponse(response);
    }

    private void logRequestHeader(ContentCachingRequestWrapper request) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            LOGGER.info("> {} {}", request.getMethod(), request.getRequestURI());
        } else {
            LOGGER.info("> {} {}?{}", request.getMethod(), request.getRequestURI(), queryString);
        }

        List<String> headerNames = Collections.list(request.getHeaderNames());
        StringBuilder headers = new StringBuilder();

        headerNames.sort(Comparator.naturalOrder());
        headerNames.forEach(headerName -> Collections.list(request.getHeaders(headerName))
                        .forEach(headerValue -> headers.append("> ")
                                        .append(headerName).append(": ")
                                        .append(headerValue).append("\n")));
        LOGGER.info("{}", headers);
    }

    private void logResponse(ContentCachingResponseWrapper response) {
        int status = response.getStatus();
        LOGGER.info(" > {} {}", status, HttpStatus.valueOf(status).getReasonPhrase());

        List<String> headerNames = new ArrayList<>(response.getHeaderNames());
        StringBuilder headers = new StringBuilder();

        headerNames.sort(Comparator.naturalOrder());
        headerNames.forEach(headerName -> response.getHeaders(headerName)
                        .forEach(headerValue -> headers.append("> ")
                                        .append(headerName).append(": ")
                                        .append(headerValue).append("\n")));
        LOGGER.info("{}", headers);

        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            LOGGER.info("Response payload: [{}]", new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}