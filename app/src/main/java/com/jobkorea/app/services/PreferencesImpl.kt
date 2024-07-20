package com.jobkorea.app.services

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceProvider {
    override val preferences: SharedPreferences
        get() = context.getSharedPreferences("default_preferences", Context.MODE_PRIVATE)
}