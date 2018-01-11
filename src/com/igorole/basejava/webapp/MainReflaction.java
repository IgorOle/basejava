package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflaction {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Method m = r.getClass().getMethod("toString");
        String s = (String) m.invoke(r);
        System.out.println(s);
    }
}
