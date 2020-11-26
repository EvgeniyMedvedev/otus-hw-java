package ru.otus.provider;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exception.AssertionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * AnnotationService.
 *
 * @author Evgeniy_Medvedev
 */
public class AnnotationProvider {

    private static int fail;
    private static int success;

    public static void test(Class<?> clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method[] before = before(clazz);
        Method[] after = after(clazz);

        for (Method method : declaredMethods) {
            Object o = null;
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    o = clazz.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    System.err.println("Не удалось создать новый экземпляр класса \n" +
                            "Class` new instance wasn`t created \n" + clazz);
                    e.printStackTrace();
                }
                try {
                    for (Method beforeMethod : before) {
                        beforeMethod.invoke(o);
                    }
                    method.invoke(o);
                    for (Method beforeMethod : after) {
                        beforeMethod.invoke(o);
                    }
                    success++;
                } catch (IllegalAccessException | InvocationTargetException | AssertionException e) {
                    Logger.getLogger("Test").warning("Не удалось выполнить метод \n" +
                            "Failed to execute method \n" + method);
                    fail++;
                    continue;
                }
            }
        }

        Logger.getLogger("Test").info(String.format("Отработано успешно - %d тестов.\n", success) + String.format("Свалилось - %d тестов", fail));
    }

    private static Method[] before(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods()).filter(method ->
                method.isAnnotationPresent(Before.class)).toArray(Method[]::new);
    }

    private static Method[] after(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods()).filter(method ->
                method.isAnnotationPresent(After.class)).toArray(Method[]::new);
    }
}