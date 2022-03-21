package com.keshmam.framework.tracking

import android.util.Log
import com.keshmam.core.tracking.Logger


class LoggerImpl :Logger{
    override fun logd(tag: String, message: String) {
        Log.d(loggerTag , "$tag -> $message" )
    }

    override fun loge(tag: String, error: Throwable) {
        Log.e(loggerTag , tag , error)
        error.printStackTrace()
    }
}