package com.camelot.p44camelotbmw.config;

import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpStatusCodeException;


@Configuration
public class RetryConfig {
    
    private static final int MAX_RETRY_ATTEMPTS = 10;
    private final NeverRetryPolicy neverRetryPolicy = new NeverRetryPolicy();
    RetryTemplate retryTemplate = new RetryTemplate();
    SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
    
    @Bean
    public RetryTemplate retryTemplate() {
        ExceptionClassifierRetryPolicy policy = new ExceptionClassifierRetryPolicy();
        policy.setExceptionClassifier(configureStatusCodeBasedRetryPolicy());
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;
    }
    
    private Classifier<Throwable, RetryPolicy> configureStatusCodeBasedRetryPolicy() {
        return throwable -> {
            if (throwable instanceof HttpStatusCodeException) {
                FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
                fixedBackOffPolicy.setBackOffPeriod(2000L);
                retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
                simpleRetryPolicy.setMaxAttempts(MAX_RETRY_ATTEMPTS);
                HttpStatusCodeException exception = (HttpStatusCodeException) throwable;
                return getRetryPolicyForStatus(exception.getStatusCode());
            }
            return simpleRetryPolicy;
        };
    }
    
    private RetryPolicy getRetryPolicyForStatus(HttpStatus httpStatus) {
        switch (httpStatus) {
            case BAD_GATEWAY:
            case SERVICE_UNAVAILABLE:
            case INTERNAL_SERVER_ERROR:
            //case UNAUTHORIZED:
            case GATEWAY_TIMEOUT:
                return simpleRetryPolicy;
            default:
                return neverRetryPolicy;
        }
    }
    
}