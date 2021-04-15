package io.github.kailuzhang.methodtrace

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by zhangshaowen on 17/6/16.
 */
class MethodTracePlugin implements Plugin<Project> {
    private static final String TAG = "ASMPlugin"

    @Override
    void apply(Project project) {

        project.extensions.create("methodTrace", MethodTraceConfig)

        String taskRequests = project.gradle.getStartParameter().getTaskRequests().toString()
        if (taskRequests.contains("Debug") || taskRequests.contains("debug")) {
            project.extensions.getByType(AppExtension).registerTransform(new MethodTraceTransform(project))
        }
    }
}