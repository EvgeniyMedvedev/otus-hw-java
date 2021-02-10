package ru.otus.bank.services;

import ru.otus.bank.models.Banknotes;

import java.util.Map;

/**
 * GiveMoneyService.
 *
 * @author Evgeniy_Medvedev
 */
public interface GiveMoneyService {

    String giveMoney(Map<Banknotes, Integer> request);
}