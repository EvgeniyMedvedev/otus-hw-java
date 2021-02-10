package ru.otus.bank.services;

import ru.otus.bank.controllers.models.BaseResponse;

/**
 * GetMoneyService.
 *
 * @author Evgeniy_Medvedev
 */
public interface GetMoneyService {

    BaseResponse getMoney(int amount);
}