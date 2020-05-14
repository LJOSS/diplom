package com.example.my_child.domain.preferences

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LivePreference<T>(
    private val updates: Observable<String>,
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: T
) : MutableLiveData<T>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onActive() {
        super.onActive()
        value = (preferences.all[key] as T) ?: defaultValue

        compositeDisposable.add(
            updates.filter { t -> t == key }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    postValue((preferences.all[it] as T) ?: defaultValue)
                }, Throwable::printStackTrace)
        )
    }

    override fun onInactive() {
        super.onInactive()
        compositeDisposable.dispose()
    }
}
