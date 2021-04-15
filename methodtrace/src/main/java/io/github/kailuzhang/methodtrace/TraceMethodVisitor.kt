package io.github.kailuzhang.methodtrace

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

class TraceMethodVisitor(
    api: Int, mv: MethodVisitor?, access: Int,
    name: String?, desc: String?, className: String?,
    private val config: Config
) : AdviceAdapter(api, mv, access, name, desc) {

    private var methodName: String? = null
    private var className: String? = null

    init {
        val traceMethod = TraceMethod.create(0, access, className, name, desc)
        this.methodName = traceMethod.getMethodNameText()
        this.className = className
    }

    private var timeLocalIndex = 0

    override fun onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        timeLocalIndex = newLocal(Type.LONG_TYPE) //这个是LocalVariablesSorter 提供的功能，可以尽量复用以前的局部变量
        mv.visitVarInsn(LSTORE, timeLocalIndex)
    }

    override fun onMethodExit(opcode: Int) {
        mv.visitLdcInsn(methodName)
        mv.visitVarInsn(LLOAD, timeLocalIndex)
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        mv.visitMethodInsn(
            INVOKESTATIC,
            config.beatClass,
            "traceMethod",
            "(Ljava/lang/String;JJ)V",
            false
        )
    }
}