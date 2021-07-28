package com.example.mvvmpractice

import android.app.Application
import com.example.mvvmpractice.data.db.ForecastDatabase

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


class ForecastApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { ForecastDatabase.invoke() }
    }
}