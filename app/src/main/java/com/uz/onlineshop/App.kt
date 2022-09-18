package com.uz.onlineshop

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk


/**
 * Created by Eldor Turgunov.
 * Online shop
 * eldorturgunov777@gmail.com
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}