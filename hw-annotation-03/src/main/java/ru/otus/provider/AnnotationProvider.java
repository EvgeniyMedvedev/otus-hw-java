package ru.otus.provider;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exception.AssertionException;
import ru.otus.test.AnnotationTest;

import java.lang.annotation.Annotation;
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
        Method[] tests = getMethodsWithAnnotation(clazz, Test.class);
        Method[] beforeMethods = getMethodsWithAnnotation(clazz, Before.class);
        Method[] afterMethods = getMethodsWithAnnotation(clazz, After.class);

        for (Method method : tests) {
            Object o = null;
            try {
                o = clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.err.println("Не удалось создать новый экземпляр класса через рефлексию \n" +
                        "Class` new instance wasn`t created throught reflection API\n" + clazz);
                o = new AnnotationTest();
            }
            try {
                for (Method beforeMethod : beforeMethods) {
                    beforeMethod.invoke(o);
                }
                method.invoke(o);
                for (Method beforeMethod : afterMethods) {
                    beforeMethod.invoke(o);
                }
                success++;
            } catch (IllegalAccessException | InvocationTargetException | AssertionException e) {
                Logger.getLogger("Test").warning("Не удалось выполнить метод \n" +
                        "Failed to execute method \n" + method);
                fail++;
            } finally {
                for (Method afterMethod : afterMethods) {
                    try {
                        afterMethod.invoke(o);
                    } catch (IllegalAccessException | InvocationTargetException | AssertionException e) {
                        Logger.getLogger("Test").warning("Не удалось выполнить метод \n" +
                                "Failed to execute method \n" + method);
                        continue;
                    }
                }
            }

        }

        Logger.getLogger("Test").info(String.format("Отработано успешно - %d тестов.\n", success) + String.format("Свалилось - %d тестов", fail));
    }

    private static Method[] getMethodsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getDeclaredMethods()).filter(method ->
                method.isAnnotationPresent(annotationClass)).toArray(Method[]::new);
    }
}