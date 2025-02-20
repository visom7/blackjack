package com.example.testPoker.model.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        context = appContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}