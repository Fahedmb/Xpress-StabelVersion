package com.glsi.xpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class XpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(XpressApplication.class, args);
    }

}
