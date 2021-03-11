package io.github.kailuzhang.methodtrace

class Config {

    //一些默认无需插桩的类
    private val UNNEED_TRACE_CLASS = arrayOf("R.class", "R$", "Manifest", "BuildConfig")

    //是否需要打印出所有被插桩的类和方法
    var needLogTraceInfo = false

    var beatClass: String? = null

    fun isNeedTraceClass(fileName: String): Boolean {
        var isNeed = true
        if (fileName.endsWith(".class")) {
            for (unTraceCls in UNNEED_TRACE_CLASS) {
                if (fileName.contains(unTraceCls)) {
                    isNeed = false
                    break
                }
            }
        } else {
            isNeed = false
        }
        return isNeed
    }

}


