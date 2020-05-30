package ru.dmdev.coronavirusapiapp

import android.app.Application
import ru.dmdev.coronavirusapiapp.di.components.DaggerAppComponent

class CoronaVirusApiApp : Application() {
    val appComponent = DaggerAppComponent.create()
}