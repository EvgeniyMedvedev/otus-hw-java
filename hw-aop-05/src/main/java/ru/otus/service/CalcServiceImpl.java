package ru.otus.service;

/**
 * CalcServiceImpl.
 *
 * @author Evgeniy_Medvedev
 */
public class CalcServiceImpl implements CalcService{

    Comparable
    @Override
    public long square(int param){
        return (long) param * param;
    }

    @Override
    public long sum(int param1, int param2) {
        return param1 + param2;
    }

    @Override
    public String sum(int param1, int param2, String message) {
        return String.format("""
                Message: %s,
                Sum: %s
                """, message, param1 + param2);
    }

    @Override
    public String sumWithoutAnnotation(int param1, int param2) {
        return String.valueOf(param1 + param2);
    }
}