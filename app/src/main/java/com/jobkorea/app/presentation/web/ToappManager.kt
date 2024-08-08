package com.jobkorea.app.presentation.web

import android.util.Log
import com.jobkorea.app.data.ActivityParams

object ToappManager : ToAppHelper() {

    fun toApp(command : String, onNavigate : (ActivityParams) -> Unit = {}) : Boolean {

        super.originalUrl = command

        Log.i("byun", "\ngoUrl: $goUrl")
        Log.i("byun", "originalUri: $originalUri")
        Log.i("byun", "lowerUri: $lowerUri")
        Log.i("byun", "lowerUrl: $lowerUrl")

        if (lowerUri.scheme == "toapp") {
            when(lowerUri.host) {
                "gosub" -> goSub(onNavigate)
            }
            return true
        } else {
            return false
        }
    }


}