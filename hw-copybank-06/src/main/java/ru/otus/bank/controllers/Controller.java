package ru.otus.bank.controllers;

import ru.otus.bank.controllers.models.BaseResponse;
import ru.otus.bank.models.Banknotes;
import ru.otus.bank.services.GetMoneyService;
import ru.otus.bank.services.GiveMoneyService;
import ru.otus.bank.services.impl.GetMoneyServiceImpl;
import ru.otus.bank.services.impl.GiveMoneyServiceImpl;

import java.util.Map;

/**
 * Controller.
 *
 * @author Evgeniy_Medvedev
 */
public class Controller {
    private static Controller controller;

    private final GiveMoneyService giveMoneyService = GiveMoneyServiceImpl.createGiveMoneyServiceImpl();
    private final GetMoneyService getMoneyService = GetMoneyServiceImpl.createGetMoneyServiceImpl();

    private Controller(){ }

    public static Controller createController(){
        if (controller != null){
            return controller;
        }
        return controller = new Controller();
    }

    public BaseResponse getMoney(int amount){
        return getMoneyService.getMoney(amount);
    }


    public String giveMoney(Map<Banknotes, Integer> request){
        return giveMoneyService.giveMoney(request);

    }
}