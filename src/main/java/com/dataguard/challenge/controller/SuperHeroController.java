package com.dataguard.challenge.controller;

import com.dataguard.challenge.exception.NotFoundException;
import com.dataguard.challenge.mapper.ExceptionMapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;

@RestController
@RequestMapping
public class SuperHeroController {
    private static final Logger logger = Logger.getLogger(SuperHeroController.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleException(NotFoundException e) {
        logger.info(String.format("## Exception: Stacktrace: %s, Cause: %s Error: %s ", e.getStackTrace(), e.getCause(), e.getMessage()));
        ExceptionMapperResponse mapper = new ExceptionMapperResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(mapper);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.info(String.format("## Exception: Stacktrace: %s, Cause: %s Error: %s ", e.getStackTrace(), e.getCause(), e.getMessage()));
        ExceptionMapperResponse mapper = new ExceptionMapperResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mapper);
    }
}
