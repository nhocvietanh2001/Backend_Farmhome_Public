package com.ute.farmhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmhomeApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(FarmhomeApplication.class, args);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
