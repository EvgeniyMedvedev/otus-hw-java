package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exception.AssertionException;
import ru.otus.repository.DIYArrayList;

import java.util.UUID;

/**
 * AnnotationTest.
 *
 * @author Evgeniy_Medvedev
 */
public class AnnotationTest {

    DIYArrayList<String> list = new DIYArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }

    @Test
    public void arrayTest(){
        if (list.size() == 0){
            throw new AssertionException();
        }
    }

    @Test
    public void createdNewObjectAfterThrowExceptionTest(){
        throw new AssertionException("Выбрасывание исключения не должно прерывать последующий процесс тестирования/ \n" +
                "Throwing an exception shouldn't interrupt the subsequent testing process");
    }

    @Test
    public void shouldWork(){
        System.out.println("Этот тест должен отработать/ \n" +
                "It`s test should run");
    }

    @After
    public void postInit(){
        list.clear();
    }


}