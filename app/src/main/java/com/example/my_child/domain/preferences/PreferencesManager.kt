package com.example.my_child.domain.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class PreferencesManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(NOKO_PREFERENCES, 0)

    private val liveSharedPreferences = LiveSharedPreferences(preferences)

    companion object {
        private const val NOKO_PREFERENCES = "NOKO_PREFERENCES"
        const val DEFAULT_FLOAT_VALUE = 0.0f
        const val DEFAULT_DOUBLE_VALUE = 0.0
        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"
        const val ZOOM = "ZOOM"
    }

    fun saveInt(key: String, value: Int) {
        preferencesModify { it.putInt(key, value) }
    }

    fun getInt(key: String, defValue: Int = 0) =
        preferences.getInt(key, defValue)

    fun saveFloat(key: String, value: Float) {
        preferencesModify { it.putFloat(key, value) }
    }

    fun getFloat(key: String) =
        preferences.getFloat(key, DEFAULT_FLOAT_VALUE)

    fun saveString(key: String, value: String) {
        preferencesModify { it.putString(key, value) }
    }

    fun getString(key: String): String? =
        preferences.getString(key, "")

    fun getBoolean(key: String, defValue: Boolean = true) =
        preferences.getBoolean(key, defValue)

    fun saveBoolean(key: String, value: Boolean) {
        preferencesModify { it.putBoolean(key, value) }
    }

    fun getLong(key: String, defValue: Long = 0L) =
        preferences.getLong(key, defValue)

    fun saveLong(key: String, value: Long) {
        preferencesModify { it.putLong(key, value) }
    }

    fun getBooleanLive(key: String, defValue: Boolean = true): LiveData<Boolean> =
        liveSharedPreferences.getBoolean(key, defValue)

    fun getIntLive(key: String, defValue: Int): LiveData<Int> =
        liveSharedPreferences.getInt(key, defValue)

    fun getLongLive(key: String, defValue: Long): LiveData<Long> =
        liveSharedPreferences.getLong(key, defValue)

    fun removeAllPreferences() {
        preferencesModify { it.clear() }
    }

    private fun preferencesModify(action: (SharedPreferences.Editor) -> Unit) = preferences
        .edit()
        .apply { action(this) }
        .apply()
}