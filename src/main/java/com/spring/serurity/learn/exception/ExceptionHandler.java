package com.spring.serurity.learn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * @author lihu
 */
@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {

    @Override
    public boolean isCausalChainsEnabled() {

        return true;
    }
}
