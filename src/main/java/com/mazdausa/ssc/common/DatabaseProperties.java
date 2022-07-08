package com.mazdausa.ssc.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseProperties {

	private String url;
	private String user;
	private String pass;
	private String driver;

}
