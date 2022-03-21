package com.keshmam.ble_client

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


infix fun Context.hasPermission(permission: String) :Boolean= run{
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

inline fun<reified T> Context.getSystemService() :T?= run{
    ContextCompat.getSystemService(this, T::class.java )
}

fun PackageManager.missingSystemFeature(name: String): Boolean = !hasSystemFeature(name)

tailrec fun Context?.activity(): AppCompatActivity? = this as? AppCompatActivity
    ?: (this as? ContextWrapper)?.baseContext?.activity()




