package io.github.kailuzhang.methodtrace

/**
 * 为MethodTrace自定义的配置项extension
 */
class MethodTraceConfig {
    String beatClass
    Set<String> configPackages

    MethodTraceConfig() {
        beatClass = null
        configPackages = null
    }
}