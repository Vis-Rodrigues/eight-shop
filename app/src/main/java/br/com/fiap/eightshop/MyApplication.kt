package br.com.fiap.eightshop

import android.app.Application
import br.com.fiap.eightshop.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)

            modules(domainModule)
        }
    }
}