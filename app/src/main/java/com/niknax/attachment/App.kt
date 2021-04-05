package com.niknax.attachment

import android.app.Application
import com.niknax.attachment.DI.AppComponent
import com.niknax.attachment.DI.DaggerAppComponent


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}