package com.niknax.attachment

import android.app.Application
import com.niknax.attachment.DI.DI
import com.niknax.attachment.data.ApiConstants
import com.niknax.attachment.data.MainRepository
import com.niknax.attachment.data.TmdbApi
import com.niknax.attachment.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            //Прикрепляем контекст
            androidContext(this@App)
            //(Опционально) подключаем зависимость
            androidLogger()
            //Инициализируем модули
            modules(listOf(DI.mainModule))
        }

    }

}