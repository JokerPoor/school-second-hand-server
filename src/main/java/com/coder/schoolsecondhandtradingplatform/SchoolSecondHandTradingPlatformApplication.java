package com.coder.schoolsecondhandtradingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication

/*
* Filter
* */
@ServletComponentScan
public class SchoolSecondHandTradingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolSecondHandTradingPlatformApplication.class, args);
    }

}
