package ru.otus.bank.configuration;

import ru.otus.bank.controllers.Controller;
import ru.otus.bank.models.Banknotes;
import ru.otus.bank.repository.BankOfSwitzerland;
import ru.otus.bank.repository.Repository;
import ru.otus.bank.services.GetMoneyService;
import ru.otus.bank.services.GiveMoneyService;
import ru.otus.bank.services.impl.GetMoneyServiceImpl;
import ru.otus.bank.services.impl.GiveMoneyServiceImpl;

import java.util.EnumMap;

/**
 * Configuration.
 *
 * @author Evgeniy_Medvedev
 */
public class Configuration {

    public static Controller controller(){
        return Controller.createController(giveMoneyService(), getMoneyService());
    }

    public static GiveMoneyService giveMoneyService(){
        return GiveMoneyServiceImpl.createGiveMoneyServiceImpl(repo());
    }

    public static GetMoneyService getMoneyService(){
        return GetMoneyServiceImpl.createGetMoneyServiceImpl(repo());
    }

    public static BankOfSwitzerland repo(){
        return Repository.createRepository(vault());
    }

    public static EnumMap<Banknotes, Integer> vault(){
        EnumMap<Banknotes, Integer> vault = new EnumMap<>(Banknotes.class);
        for (Banknotes banknote : Banknotes.values()) {
            vault.put(banknote, 50);
        }

        return vault;
    }
}