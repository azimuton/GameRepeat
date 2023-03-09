package com.example.gamerepeat

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "34db36ab-6eed-44d0-8ae1-8f3b09488048"

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}