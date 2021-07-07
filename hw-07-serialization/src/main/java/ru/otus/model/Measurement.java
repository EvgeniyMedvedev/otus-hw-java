package ru.otus.model;


import java.io.Serializable;

//Допустим, этот класс библиотечный, его нельзя менять
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final double value;

    public Measurement() {
        this.name = null;
        this.value = 0.0;
    }

    public Measurement(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
