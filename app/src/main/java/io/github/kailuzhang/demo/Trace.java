package io.github.kailuzhang.demo;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Create by cxzheng on 2019/8/26
 * 全方法插桩内容
 */
public class Trace {
    private static String THRESHOLD_TIME = "THRESHOLD_TIME";

    public static void traceMethod(String name, long startTime, long endTime) {
        Log.d("MethodTrace", String.valueOf(endTime - startTime));
    }

    public static int getThresholdTime(Context context) {
        return 50;
    }

    public static void setThresholdTime(Context context, int value) {

    }

    private static MethodInfo createMethodInfo(Entity startEntity, Entity endEntity) {
        return new MethodInfo(startEntity.name,
                endEntity.time - startEntity.time, startEntity.isMainThread);
    }


    private static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static boolean isOpenTraceMethod() {
        return true;
    }

    static class Entity {
        public String name;
        public Long time;
        public boolean isStart;
        public int pos;
        public boolean isMainThread;

        public Entity(String name, Long time, boolean isStart, boolean isMainThread) {
            this.name = name;
            this.time = time;
            this.isStart = isStart;
            this.isMainThread = isMainThread;
        }
    }

}
