package com.mazdausa.ssc.service.impl;

import java.util.Objects;
import java.util.function.BiFunction;

import org.springframework.http.HttpStatus;

import com.mazdausa.ssc.dao.GenericResponse;


public class GenericResponseWrapper {
	
    public static BiFunction<Object, Object, GenericResponse> GenericResponseFunction = (s, e) -> {
        HttpStatus httpStatus = Objects.nonNull(s) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new GenericResponse(httpStatus.getReasonPhrase(), s,
                Objects.nonNull(s),
                httpStatus.value(),
                e);
    };

}
