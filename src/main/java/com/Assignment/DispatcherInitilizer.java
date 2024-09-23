package com.Assignment;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class arr[]={SpringConfig.class};
        return arr;
    }

    @Override
    protected String[] getServletMappings() {
        String arr[]={"/home/*"};
        return arr;
    }
}