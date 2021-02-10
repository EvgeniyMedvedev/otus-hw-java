package ru.otus.service;

import ru.otus.annotation.Log;

/**
 * CalcService.
 *
 * @author Evgeniy_Medvedev
 */
public interface CalcService {
    
    @Log
    long square(int param);

    @Log
    long sum(int param1, int param2);

    @Log
    String sum(int param1, int param2, String value);

    String sumWithoutAnnotation(int param1, int param2);
}