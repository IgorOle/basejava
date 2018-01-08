package com.igorole.basejava.webapp;

import com.igorole.basejava.webapp.model.Resume;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainReflaction {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {
        Resume r = new Resume();
        Method m = r.getClass().getMethod("toString");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.get(r));
        String s = (String) m.invoke(r, );



    }
}
