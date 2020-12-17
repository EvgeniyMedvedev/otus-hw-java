package ru.otus.generator;

import lombok.SneakyThrows;
import lombok.val;
import ru.otus.GcDemo;
import ru.otus.repository.DIYArrayList;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

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

    private static final Map<String, int[]> configure = Map.
            of(DEFAULT_G1, new int[]{2450, 1},
            G1_WITH_LOW_MEMORY, new int[]{290, 1},
            ZGC_WITH_LOW_MEMORY, new int[]{110, 1},
            DEFAULT_ZGC, new int[]{1000, 1});

    public static List<String> callSOF(long cursor) {
        List<String> strings = List.of(cursor + UUID.randomUUID().toString());
        if (cursor < 20) {
            return strings;
        }
        cursor -= 10;
        return callSOF(cursor);
    }

    public static List<String> callOOM() throws InterruptedException {
        List<String[]> strings = new DIYArrayList<>();
        int[] ints = configure.get(DEFAULT_G1);
        for (; ; ) {
            strings.add(new String[ints[0] + new Random().nextInt(ints[0])]);
            Thread.sleep(ints[1]);
        }
    }

    public static void GClog() {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("runtime.total = " + runtime.totalMemory());
        System.out.println("runtime.free = " + runtime.freeMemory());

        for (int i = 0; i < 1_000_000_0; i++) {
            UUID uuid = UUID.randomUUID();
            uuid = null;
        }
        callSOF(10_000_000);
        Logger.getLogger(GcDemo.class.getName()).info("");

        System.out.println("runtime.total = " + runtime.totalMemory());
        System.out.println("runtime.free = " + runtime.freeMemory());
    }
}