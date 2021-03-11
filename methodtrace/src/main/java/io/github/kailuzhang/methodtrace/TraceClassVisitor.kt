package io.github.kailuzhang.methodtrace

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class TraceClassVisitor(api: Int, cv: ClassVisitor?, private val traceConfig: Config) :
    ClassVisitor(api, cv) {

    private var className: String? = null
    private var isABSClass = false
    private var isBeatClass = false

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)

        this.className = name
        //抽象方法或者接口
        if (access and Opcodes.ACC_ABSTRACT > 0 || access and Opcodes.ACC_INTERFACE > 0) {
            this.isABSClass = true
        }

        //插桩代码所属类
        val resultClassName = name?.replace(".", "/")
        if (resultClassName == traceConfig.beatClass) {
            this.isBeatClass = true
        }


        val isNotNeedTraceClass = isABSClass
        if (traceConfig.needLogTraceInfo && !isNotNeedTraceClass) {
            println("MethodTraceMan-trace-class: ${className ?: "未知"}")
        }
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        desc: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val isConstructor = MethodFilter.isConstructor(name)
        return if (isABSClass || isBeatClass || isConstructor) {
            super.visitMethod(access, name, desc, signature, exceptions)
        } else {
            val mv = cv.visitMethod(access, name, desc, signature, exceptions)
            TraceMethodVisitor(api, mv, access, name, desc, className, traceConfig)
        }
    }
}