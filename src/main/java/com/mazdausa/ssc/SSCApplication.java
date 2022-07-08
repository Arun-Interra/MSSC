package com.mazdausa.ssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MFPCommonService
public class SSCApplication {

	protected SSCApplication() {
		super();
	}

	public static void main(String[] args) {
		SpringApplication.run(SSCApplication.class, args);
	}

}
