package ru.otus;

import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryG1;
import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryG1WithLowMemory;
import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryParallel;
import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryParallelWithLowMemory;
import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryZGC;
import static ru.otus.generator.JVMErrorGenerator.throwOutOfMemoryZGCWithLowMemory;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * GcDemo.
 *
 * @author Evgeniy_Medvedev
 */
public class GcDemo {

    private static int oldGenerationCount;
    private static int yongGenerationCount;
    private static long totalTimeGC;

    public static void main(String[] args) {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        try {
//            throwOutOfMemoryG1();
//            throwOutOfMemoryG1WithLowMemory();
//            throwOutOfMemoryZGC();
//            throwOutOfMemoryZGCWithLowMemory();
            throwOutOfMemoryParallel();
//            throwOutOfMemoryParallelWithLowMemory();
        }catch (OutOfMemoryError er){
            System.out.printf("time: %d , count generation young - %d and old - %d%n and total working time - %d \n",
                    (System.currentTimeMillis() - beginTime) / 1000,
                    yongGenerationCount, oldGenerationCount, totalTimeGC);
            er.printStackTrace();
        }

    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    oldGenerationCount += gcAction.contains("major") ? 1 : 0;
                    yongGenerationCount += gcAction.contains("minor") ? 1 : 0;

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");

                    totalTimeGC += duration;
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }

}