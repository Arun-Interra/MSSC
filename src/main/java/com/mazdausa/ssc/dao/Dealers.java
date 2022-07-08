package com.mazdausa.ssc.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = false)
@Data
public class Dealers {

	private List<DealerInfo> dealers;
		
}
