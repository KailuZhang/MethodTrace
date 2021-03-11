package io.github.kailuzhang.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread {
            while (true) {
                Trace.obtainMethodCostData().forEach {
                    if (it.costTime > 100) {
                        it.log()
                    }
                }
                Trace.resetTraceManData()
                Thread.sleep(500)
            }
        }
    }
}