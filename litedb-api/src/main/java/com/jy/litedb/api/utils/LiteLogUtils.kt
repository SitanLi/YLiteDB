package com.jy.litedb.api.utils

import android.util.Log
import java.util.*

/**
 * @Author jy
 * @Date 2019/8/8-16:53
 * @TODO 打印log
 */
object LiteLogUtils {

    var SHOW_LOG = true
    private const val TAG = "LiteDB"
    private var level = LogLevel.ALL

    enum class LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        ALL
    }

    fun setLogLevel(logLevel: LogLevel) {
        level = logLevel
    }

    //*******************************Format方式*********************************************
    fun eFormat(msgFormat: String, vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            val message = String.format(msgFormat, *args)
            log(
                TAG,
                traceElement,
                message,
                LogLevel.ERROR
            )
        }
    }

    fun iFormat(msgFormat: String, vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            val message = String.format(msgFormat, *args)
            log(
                TAG,
                traceElement,
                message,
                LogLevel.INFO
            )
        }
    }


    fun wFormat(msgFormat: String, vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            val message = String.format(msgFormat, *args)
            log(
                TAG,
                traceElement,
                message,
                LogLevel.WARN
            )
        }
    }


    fun dFormat(msgFormat: String, vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            val message = String.format(msgFormat, *args)
            log(
                TAG,
                traceElement,
                message,
                LogLevel.DEBUG
            )
        }
    }

    //***********************************非Format方式**********************************************

    fun e(vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            log(
                TAG,
                traceElement,
                getString(*args),
                LogLevel.ERROR
            )
        }
    }

    fun i(vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            log(
                TAG,
                traceElement,
                getString(*args),
                LogLevel.INFO
            )
        }
    }

    fun w(vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            log(
                TAG,
                traceElement,
                getString(*args),
                LogLevel.WARN
            )
        }
    }

    fun d(vararg args: Any?) {
        if (SHOW_LOG) {
            val traceElement = Thread.currentThread().stackTrace[3]
            log(
                TAG,
                traceElement,
                getString(*args),
                LogLevel.DEBUG
            )
        }
    }

    private fun getString(vararg args: Any?): String {
        return when {
            args.size == 1 -> args[0].toString()
            else -> {
                val message = StringBuilder()
                for (`object` in args) {
                    message.append(`object`)
                    message.append("---")
                }
                message.toString()
            }
        }
    }

    private fun log(
        tag: String,
        traceElement: StackTraceElement,
        message: String,
        logLevel: LogLevel
    ) {
        val msgFormat = "[ %s %s ]---类名：%s---方法名：%s---第%d行---信息---%s"
        val messageWithTime = String.format(
            Locale.CHINA,
            msgFormat,
            LiteTimeUtils.logStr,
            logLevel.name,
            traceElement.fileName,
            traceElement.methodName,
            traceElement.lineNumber,
            message
        )
        if (level == LogLevel.ALL || logLevel == level) {
            when (logLevel) {
                LogLevel.INFO -> Log.i(tag, messageWithTime)
                LogLevel.WARN -> Log.w(tag, messageWithTime)
                LogLevel.DEBUG -> Log.d(tag, messageWithTime)
                LogLevel.ERROR -> Log.e(tag, messageWithTime)
                else -> Log.i(tag, messageWithTime)
            }
        }
    }

}
