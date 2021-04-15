package io.github.kailuzhang.tracelog;

import android.os.Looper;
import android.util.Log;

public class Trace {

    private static final String TAG = "MethodTrace";

    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final String TOP_CORNER = "┌";
    private static final String MIDDLE_CORNER = "├";
    private static final String LEFT_BORDER = "│ ";
    private static final String BOTTOM_CORNER = "└";
    private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String TOP_BORDER = TOP_CORNER + SIDE_DIVIDER + SIDE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + MIDDLE_DIVIDER + MIDDLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_CORNER + SIDE_DIVIDER + SIDE_DIVIDER;

    private static int thresholdTime = 50;

    private static boolean isOpenTraceMethod = true;

    public static void traceMethod(String name, long startTime, long endTime) {
        if (isOpenTraceMethod) {
            long costTime = endTime - startTime;
            if (costTime > thresholdTime) {
                boolean isMainThread = isInMainThread();
                String msg = LINE_SEP + TOP_BORDER + LINE_SEP +
                        LEFT_BORDER + name + LINE_SEP + MIDDLE_BORDER + LINE_SEP +
                        LEFT_BORDER + "costTime: " + costTime + LINE_SEP +
                        LEFT_BORDER + "isMainThread: " + isMainThread +
                        LINE_SEP + BOTTOM_BORDER;
                Log.d(TAG, msg);
            }
        }
    }

    public static void setThresholdTime(int value) {
        thresholdTime = value;
    }


    private static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void setOpenTraceMethod(boolean open) {
        isOpenTraceMethod = open;
    }

}
