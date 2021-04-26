package com.niknax.attachment

import android.app.Application
import com.niknax.attachment.DI.AppComponent
import com.niknax.attachment.DI.DaggerAppComponent
import com.niknax.attachment.DI.modules.DatabaseModule
import com.niknax.attachment.DI.modules.DomainModule
import com.niknax.attachment.DI.modules.RemoteModule



class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        //dagger = DaggerAppComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}