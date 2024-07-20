package com.jobkorea.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JobkoreaApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}