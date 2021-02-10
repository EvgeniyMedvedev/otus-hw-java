package ru.otus.interceptor;

import ru.otus.annotation.Log;
import ru.otus.service.CalcService;
import ru.otus.service.CalcServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Interceptor.
 *
 * @author Evgeniy_Medvedev
 */
public class Interceptor {

    private Interceptor() {}

    public static CalcService createCalc() {
        InvocationHandler handler = new DemoInvocation(new CalcServiceImpl());
        return (CalcService) Proxy.newProxyInstance(Interceptor.class.getClassLoader(),
                CalcServiceImpl.class.getInterfaces(), handler);
    }

    static class DemoInvocation implements InvocationHandler {
        private final CalcService myClass;
        private final List<Method> methods;

        private DemoInvocation(CalcService myClass) {
            this.myClass = myClass;
            this.methods = Arrays.stream(this.myClass.getClass().getInterfaces())
                    .flatMap(interFace -> Arrays.stream(interFace.getMethods()))
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .collect(Collectors.toList());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (methods.contains(method)) {
                System.out.println("name of an executed method:" + method.getName() + ", parameter(s) " + Arrays.toString(args));
            }

            return method.invoke(myClass, args);
        }
    }
}