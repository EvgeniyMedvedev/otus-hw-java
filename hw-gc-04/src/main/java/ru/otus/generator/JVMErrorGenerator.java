package ru.otus.generator;

import ru.otus.repository.DIYArrayList;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * JVMErrorGenerator.
 *
 * О формате логов
 * http://openjdk.java.net/jeps/158
 *
 * -Xms512m
 * -Xmx512m
 * -Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=./logs/dump
 * -XX:+UseG1GC
 * -XX:+UseZGC
 * 1)
 * default, time: 83 sec (82 without Label_1)
 * 2)
 * -XX:MaxGCPauseMillis=100000, time: 82 sec //Sets a target for the maximum GC pause time.
 *
 * 3)
 * -Xms2048m
 * -Xmx2048m
 * time: 81 sec
 *
 * @author Evgeniy_Medvedev
 */
public class JVMErrorGenerator {

    private static final String DEFAULT_G1 = "G1 default";
    private static final String G1_WITH_LOW_MEMORY = "G1 with low memory";
    private static final String ZGC_WITH_LOW_MEMORY = "ZGC with low memory";
    private static final String DEFAULT_ZGC = "ZGC default";
    private static final String PARALLEL = "PARALLEL default";
    private static final String PARALLEL_WITH_LOW_MEMORY = "PARALLEL with low memory";

    private static final Map<String, Integer> configure = Map.
            of(DEFAULT_G1, 7700,
            G1_WITH_LOW_MEMORY, 850,
            ZGC_WITH_LOW_MEMORY, 400,
            DEFAULT_ZGC, 3500,
            PARALLEL, 7000,
            PARALLEL_WITH_LOW_MEMORY, 660);

    public static List<String> callSOF(long cursor) {
        List<String> strings = List.of(cursor + UUID.randomUUID().toString());
        if (cursor < 20) {
            return strings;
        }
        cursor -= 10;
        return callSOF(cursor);
    }

    public static void throwOutOfMemoryG1() {
        throwOOM(DEFAULT_G1);
    }

    public static void throwOutOfMemoryG1WithLowMemory() {
        throwOOM(G1_WITH_LOW_MEMORY);
    }

    public static void throwOutOfMemoryZGC() {
        throwOOM(DEFAULT_ZGC);
    }

    public static void throwOutOfMemoryZGCWithLowMemory() {
        throwOOM(ZGC_WITH_LOW_MEMORY);
    }

    public static void throwOutOfMemoryParallel() {
        throwOOM(PARALLEL);
    }

    public static void throwOutOfMemoryParallelWithLowMemory() {
        throwOOM(PARALLEL_WITH_LOW_MEMORY);
    }

    private static void throwOOM(String parameter) {
        List<String[]> strings = new DIYArrayList<>();
        int capacity = configure.get(parameter);
        for (int i = 1; ; i++) {
            strings.add(new String[capacity]);
            if (i % 2 == 0) {
                strings.remove(strings.size() - 1);
                i = 0;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}