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

    private final GiveMoneyService giveMoneyService;
    private final GetMoneyService getMoneyService;

    private Controller(GiveMoneyService giveMoneyService, GetMoneyService getMoneyService){
        this.giveMoneyService = giveMoneyService;
        this.getMoneyService = getMoneyService;
    }

    public static Controller createController(GiveMoneyService giveMoneyService, GetMoneyService getMoneyService){
        if (controller != null){
            return controller;
        }
        return controller = new Controller(giveMoneyService, getMoneyService);
    }

    public BaseResponse getMoney(int amount){
        return getMoneyService.getMoney(amount);
    }


    public String giveMoney(Map<Banknotes, Integer> request){
        return giveMoneyService.giveMoney(request);

    }
}