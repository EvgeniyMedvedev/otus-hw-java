package ru.otus.bank.repository;

import ru.otus.bank.controllers.models.BaseResponse;
import ru.otus.bank.models.Banknotes;

import java.util.Map;

/**
 * BankOfSwitzerland.
 *
 * @author Evgeniy_Medvedev
 */
public interface BankOfSwitzerland {

    BaseResponse get(int amount);

    String give(Map<Banknotes, Integer> request);
}