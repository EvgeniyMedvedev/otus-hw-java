package ru.otus.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static ru.otus.bank.configuration.Configuration.vault;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.bank.configuration.Configuration;
import ru.otus.bank.controllers.Controller;
import ru.otus.bank.controllers.models.BaseResponse;
import ru.otus.bank.models.Banknotes;
import ru.otus.bank.repository.Repository;

import java.util.Map;

/**
 * TerminalTest.
 *
 * @author Evgeniy_Medvedev
 */
public class TerminalTest {


    private final Controller controller = Configuration.controller();

    @BeforeEach
    public void init(){
        Repository.createRepository(vault()).clearContext();
    }

    @Test
    public void getTest(){
        assertEquals(6340, controller.getMoney(6340).getValue());
    }

    @Test
    public void getWrongTest(){
        int amount = 6345;
        BaseResponse response = controller.getMoney(amount);

        assertNotEquals(amount, response.getValue());
        assertEquals("Такая сумма недоступна для выдачи, введите кратную 10", response.getErrorMsg());
    }

    @Test
    public void getTenFromEmptyRepoTest(){
        int amount = 10;
        BaseResponse response = null;

        for (int i = 0; i < 100; i++) {
            response = controller.getMoney(amount);
        }
        assertNotEquals(amount, response.getValue());
        assertEquals("Десяток нет, иди работай", response.getErrorMsg());
    }

    @Test
    public void getEmptyTest(){
        int amount = 100;
        BaseResponse response = null;

        for (int i = 0; i < 100; i++) {
            response = controller.getMoney(amount);
        }
        assertNotEquals(amount, response.getValue());
        assertEquals("Я пуст внутри", response.getErrorMsg());
    }

    @Test
    public void giveTest(){
        Map<Banknotes, Integer> amount = Map.of(Banknotes.TEN, 10);
        String msg = controller.giveMoney(amount);

        assertEquals("Готово", msg);
    }

    @Test
    public void giveWrongTest(){
        Map<Banknotes, Integer> amount = Map.of(Banknotes.TEN, 1);
        String msg = controller.giveMoney(amount);

        assertEquals("Готово", msg);
    }

    @Test
    public void giveWrongFiftyTest(){
        Map<Banknotes, Integer> amount = Map.of(Banknotes.FIFTY, 50);
        String msg = controller.giveMoney(amount);

        assertEquals("Готово", msg);
    }
}