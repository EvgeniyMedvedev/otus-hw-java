package ru.otus.bank.services.impl;

import ru.otus.bank.models.Banknotes;
import ru.otus.bank.repository.BankOfSwitzerland;
import ru.otus.bank.repository.Repository;
import ru.otus.bank.services.GiveMoneyService;

import java.util.Map;

/**
 * GiveMoneyServiceImpl.
 *
 * @author Evgeniy_Medvedev
 */
public class GiveMoneyServiceImpl implements GiveMoneyService {

    private static GiveMoneyService service;
    private final BankOfSwitzerland repository;

    private GiveMoneyServiceImpl(BankOfSwitzerland repository){
        this.repository = repository;
    }

    public static GiveMoneyService createGiveMoneyServiceImpl(BankOfSwitzerland repository) {
        if (service != null) {
            return service;
        }
        return service = new GiveMoneyServiceImpl(repository);
    }

    @Override
    public String giveMoney(Map<Banknotes, Integer> request) {
        return repository.give(request);
    }
}