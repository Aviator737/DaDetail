package ru.dadetail

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import ru.dadetail.di.appModule

class DaDetailApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}
