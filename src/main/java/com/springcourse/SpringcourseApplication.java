package com.springcourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcourseApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringcourseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringcourseApplication.class, args);
		logger.debug("Mi mensaje Debug");

	}

}
