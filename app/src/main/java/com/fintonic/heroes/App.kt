package com.fintonic.heroes

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    companion object {
        lateinit var appInstance: Application
        lateinit var mRequestQueue: RequestQueue
    }


    private val modules = listOf(heroModule, networkModule)

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        mRequestQueue = Volley.newRequestQueue(applicationContext)
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }

}