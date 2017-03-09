package com.example.bxh.sayhello.inject;

import android.app.Activity;
import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by bxh on 1/24/17.
 */

public class InjectUtils {
    public static void autoInjectAllField(Activity context) {
        try {
            Class<?> clazz = context.getClass();
            Field[] fields = clazz.getDeclaredFields();//获得Activity中声明的字段
            for (Field field : fields) {
                // 查看这个字段是否有我们自定义的注解类标志的
                if (field.isAnnotationPresent(ViewInject.class)) {
                    ViewInject inject = field.getAnnotation(ViewInject.class);
                    int id = inject.value();
                    if (id > 0) {
                        field.setAccessible(true);
                        field.set(context, context.findViewById(id));//给我们要找的字段设置值
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
