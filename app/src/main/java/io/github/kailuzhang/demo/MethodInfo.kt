package io.github.kailuzhang.demo

import android.util.Log

/**
 * Create by cxzheng on 2019/9/9
 */
data class MethodInfo(var name: String?, var costTime: Long, var isMainThread: Boolean) {
    override fun toString(): String {
        return " $LINE_SEP$TOP_BORDER$LINE_SEP" +
            "$LEFT_BORDER$name$LINE_SEP$MIDDLE_BORDER$LINE_SEP" +
            "$LEFT_BORDER${"costTime: "}$costTime$LINE_SEP" +
            "$LEFT_BORDER${"isMainThread: "}$isMainThread" +
            "$LINE_SEP$BOTTOM_BORDER"
    }

    fun log() {
        Log.d("t", toString())
    }
}

private val LINE_SEP = System.getProperty("line.separator")
private const val TOP_CORNER = "┌"
private const val MIDDLE_CORNER = "├"
private const val LEFT_BORDER = "│ "
private const val BOTTOM_CORNER = "└"
private const val SIDE_DIVIDER = "────────────────────────────────────────────────────────"
private const val MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
private const val TOP_BORDER = TOP_CORNER + SIDE_DIVIDER + SIDE_DIVIDER
private const val MIDDLE_BORDER = MIDDLE_CORNER + MIDDLE_DIVIDER + MIDDLE_DIVIDER
private const val BOTTOM_BORDER: String = BOTTOM_CORNER + SIDE_DIVIDER + SIDE_DIVIDER