package com.bhagirath.mocking_frameworks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockingFrameworksApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockingFrameworksApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MockingFrameworksApplication.class, args);
        MDC.put("keyValue", "someValue");
        User user = new User();
        user.setAddress("someAddress");
        LOGGER.info(JsonLog.builder().withInput(user).withMessage("someMessage").build().getProperties().toString());
    }
}
