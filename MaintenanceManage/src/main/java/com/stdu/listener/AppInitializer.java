package com.stdu.listener;

import com.stdu.generator.AutoOrderGenerator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {
    private AutoOrderGenerator orderGenerator = new AutoOrderGenerator();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        orderGenerator.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        orderGenerator.shutdown();
    }
}