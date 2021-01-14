package ru.otus.interceptor;

import ru.otus.annotation.Log;
import ru.otus.service.CalcService;
import ru.otus.service.CalcServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Interceptor.
 *
 * @author Evgeniy_Medvedev
 */
public class Interceptor {

    private Interceptor(){}

    public static CalcService createCalc(){
        InvocationHandler handler = new DemoInvocation(new CalcServiceImpl());
        return (CalcService) Proxy.newProxyInstance(Interceptor.class.getClassLoader(),
                CalcServiceImpl.class.getInterfaces(), handler);
    }

    static class DemoInvocation implements InvocationHandler {
        private final CalcService myClass;

        DemoInvocation(CalcService myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class)) {
                System.out.println("name of an executed method:" + method.getName() + ", parameters " + Arrays.toString(args));
            }

            switch (method.getName()){
                case ("square") :
                    return myClass.square((int)args[0]);

                case ("sum") :
                    if (args.length > 2){
                        return myClass.sum((int)args[0], (int)args[1], (String) args[2]);
                    }
                    return myClass.sum((int)args[0], (int)args[1]);

            }
            return myClass.sumWithoutAnnotation((int)args[0], (int)args[1]);
        }
    }
}