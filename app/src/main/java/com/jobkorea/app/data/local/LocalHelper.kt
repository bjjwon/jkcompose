package com.jobkorea.app.data.local

import com.jobkorea.app.services.PreferenceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalHelper @Inject constructor(
    private val provider: PreferenceProvider
) {

    internal operator fun set(key: String, value: Any?) {

        val editor = provider.preferences.edit()

        when (value) {
            is String? -> editor.putString(key, value ?: "")
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        editor.apply()
    }

    internal inline operator fun <reified T : Any> get(key: String, defaultValue: T? = null): T? = with(provider.preferences) {

        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }


    fun remove(key: String) {
        val editor = provider.preferences.edit()
        editor.remove(key)
        editor.apply()
    }

}