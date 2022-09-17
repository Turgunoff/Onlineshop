package com.uz.onlineshop

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}