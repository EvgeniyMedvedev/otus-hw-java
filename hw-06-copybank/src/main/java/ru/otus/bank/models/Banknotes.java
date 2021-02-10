package ru.otus.bank.models;

import lombok.Getter;

/**
 * Banknotes.
 *
 * @author Evgeniy_Medvedev
 */
@Getter
public enum Banknotes {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int value;

    Banknotes(int value){
        this.value = value;
    }
}