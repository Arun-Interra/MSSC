package com.mazdausa.ssc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {
	
	    private String desc;
	    private Object result;
	    private boolean success;
	    private int status;
	    private Object error;
}
