package io.github.kailuzhang.methodtrace

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodVisitor(
    api: Int, mv: MethodVisitor?, access: Int,
    name: String?, desc: String?, className: String?,
    private val config: Config
) : AdviceAdapter(api, mv, access, name, desc) {

    private var methodName: String? = null
    private var className: String? = null
    private val maxSectionNameLength = 127

    init {
        val traceMethod = TraceMethod.create(0, access, className, name, desc)
        this.methodName = traceMethod.getMethodNameText()
        this.className = className
    }

    override fun onMethodEnter() {
        mv.visitLdcInsn(generatorMethodName())
        mv.visitMethodInsn(
            INVOKESTATIC,
            config.beatClass,
            "start",
            "(Ljava/lang/String;)V",
            false
        )
    }

    override fun onMethodExit(opcode: Int) {
        mv.visitLdcInsn(generatorMethodName())
        mv.visitMethodInsn(
            INVOKESTATIC,
            config.beatClass,
            "end",
            "(Ljava/lang/String;)V",
            false
        )
    }

    private fun generatorMethodName(): String? {
        var sectionName = methodName
        var length = sectionName?.length ?: 0
        if (length > maxSectionNameLength && !sectionName.isNullOrBlank()) {
            // 先去掉参数
            val paramIndex = sectionName.indexOf('(')
            sectionName = sectionName.substring(0, paramIndex)
            // 如果依然更大，直接裁剪
            length = sectionName.length
            if (length > 127) {
                sectionName = sectionName.substring(length - maxSectionNameLength)
            }
        }
        return sectionName
    }
}