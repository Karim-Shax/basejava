package com.urise.webapp;

import com.urise.webapp.model.Resume;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume=new Resume("uuid3");

        Class<?> aClass =resume.getClass();

        Method method = aClass.getMethod("toString");

        System.out.println(method.invoke(resume));
    }
}
