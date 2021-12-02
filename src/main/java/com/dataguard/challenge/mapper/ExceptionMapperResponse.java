package com.dataguard.challenge.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionMapperResponse implements Serializable {
    private String message;
}
