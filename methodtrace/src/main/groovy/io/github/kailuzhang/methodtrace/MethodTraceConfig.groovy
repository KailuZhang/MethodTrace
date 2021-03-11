package io.github.kailuzhang.methodtrace

/**
 * 为TraceMan自定义的配置项extension
 */
class MethodTraceConfig {
    boolean open
    boolean logTraceInfo
    String beatClass

    MethodTraceConfig() {
        open = true
        logTraceInfo = false
        beatClass = null
    }
}