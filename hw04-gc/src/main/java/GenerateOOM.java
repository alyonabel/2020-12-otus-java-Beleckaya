import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GenerateOOM {

/*
G1

-Xms512m
-Xmx1512m
-verbose:gc
-Xloggc:C:\logs\G1.log
-XX:+PrintGCDetails
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=C:\logs\dump
-XX:+UseG1GC

Serial Collector

-Xms512m
-Xmx1512m
-verbose:gc
-Xloggc:C:\logs\Serial.log
-XX:+PrintGCDetails
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=C:\logs\dump
-XX:+UseSerialGC

Parallel Collector

-Xms512m
-Xmx1512m
-verbose:gc
-Xloggc:C:\logs\Parallel.log
-XX:+PrintGCDetails
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=C:\logs\dump
-XX:+UseParallelGC
*/


    public static void main(String[] args) throws Exception {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        switchOnMonitoring();

      GenerateOOM memoryTest = new GenerateOOM();
      memoryTest.generateOOM();
    }


    public void generateOOM() throws Exception {
        int iterator= 30;
        System.out.println("\n OOM test started..\n");
        for (int i = 1; i < 30; i++) {
            System.out.println("Iteration N" + i + ". Free Memory: " + Runtime.getRuntime().freeMemory());
            int loop = 2;
          int[] memoryFill = new int[iterator];
            do {
                memoryFill[loop] = 0;
                loop--;
            } while (loop > 0);
            iterator = iterator * 5;
            System.out.println("\nRequired Memory for next loop: " + iterator);
            Thread.sleep(5000);
        }
    }


    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }




}