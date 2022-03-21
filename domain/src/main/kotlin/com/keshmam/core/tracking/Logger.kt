package com.keshmam.core.tracking

interface Logger {
    val loggerTag: String
        get() = "Logger"

    fun logd(tag:String , message :String)
    fun loge(tag:String  , error : Throwable)
}