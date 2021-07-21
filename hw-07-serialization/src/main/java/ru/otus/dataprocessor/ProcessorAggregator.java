package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        /**
         * Можно и так data.stream()
         *  .collect(Collectors.groupingBy(Measurement::getName, Collectors.summarizingDouble(Measurement::getValue)));
         *
         * @return Map<String, DoubleSummaryStatistics>
         *
         *   DoubleSummaryStatistics - имеет методы
         *
         *      getAverage(): возвращает среднее значение
         *
         *      getCount(): возвращает количество элементов в наборе
         *
         *      getMax(): возвращает максимальное значение
         *
         *      getMin(): возвращает минимальное значение
         *
         *      getSum(): возвращает сумму элементов
         *
         *      accept(): добавляет в набор новый элемент
         * */
        return Optional.ofNullable(data).orElse(new ArrayList<>()).stream().collect(Collectors.groupingBy(Measurement::getName, Collectors.summingDouble(Measurement::getValue)));
    }
}
