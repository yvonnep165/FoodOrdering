package com.onlinefoodordering.onlineOrder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // Spring manages to close the ApplicationContext.
    }
}

