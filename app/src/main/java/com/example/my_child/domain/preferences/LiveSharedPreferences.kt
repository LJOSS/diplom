package com.example.my_child.domain.preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import io.reactivex.subjects.PublishSubject

class LiveSharedPreferences(private val preferences: SharedPreferences) {

    private val publisher = PublishSubject.create<String>()
    private val listener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key -> publisher.onNext(key) }

    private val updates = publisher.doOnSubscribe {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }.doOnDispose {
        if (!publisher.hasObservers())
            preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun getBoolean(key: String, defaultValue: Boolean): LivePreference<Boolean> =
        LivePreference(
            updates,
            preferences,
            key,
            defaultValue
        )

    fun getInt(key: String, defaultValue: Int): LiveData<Int> =
        LivePreference(
            updates,
            preferences,
            key,
            defaultValue
        )

    fun getLong(key: String, defaultValue: Long): LiveData<Long> =
        LivePreference(
            updates,
            preferences,
            key,
            defaultValue
        )
}