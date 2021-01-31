package ru.otus.bank.services.impl;

import ru.otus.bank.controllers.models.BaseResponse;
import ru.otus.bank.repository.BankOfSwitzerland;
import ru.otus.bank.repository.Repository;
import ru.otus.bank.services.GetMoneyService;

/**
 * GetMoneyServiceImpl.
 *
 * @author Evgeniy_Medvedev
 */
public class GetMoneyServiceImpl implements GetMoneyService {

    private static GetMoneyService service;

    private final BankOfSwitzerland repository = Repository.createRepository();

    private GetMoneyServiceImpl(){ }

    public static GetMoneyService createGetMoneyServiceImpl() {
        if (service != null) {
            return service;
        }
        return service = new GetMoneyServiceImpl();
    }

    @Override
    public BaseResponse getMoney(int amount) {
        return repository.get(amount);
    }
}