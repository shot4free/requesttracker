package com.training.requesttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by sony on 7/22/2017.
 */
@SpringBootApplication
public class SpringBootApp {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(SpringBootApp.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
