package com.myapp.data.core

import java.io.BufferedReader
import java.io.InputStreamReader

object KillRunningWinProcesses {
    private const val TASKLIST = "tasklist"
    private const val KILL = "taskkill /F /IM "

    @Throws(java.lang.Exception::class)
    fun isProcessRunning(serviceName: String?): Boolean {
        val p: Process = Runtime.getRuntime().exec(TASKLIST)
        val reader = BufferedReader(
            InputStreamReader(
                p.inputStream
            )
        )
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            if (line!!.contains(serviceName!!)) {
                return true
            }
        }
        return false
    }

    @Throws(java.lang.Exception::class)
    fun killProcess(serviceName: String) {
        Runtime.getRuntime().exec(KILL + serviceName)
    }
}