package com.example.my_child.presentation.parent.pickup

import androidx.lifecycle.ViewModel
import com.example.my_child.data.api.MyChildApi
import com.example.my_child.data.api.dto.data.PickupData
import com.example.my_child.data.api.dto.response.DefaultResponse
import com.example.my_child.domain.preferences.PreferencesManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PickupViewModel(
    private val myChildApi: MyChildApi,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun getPickup(teacherId: Int, childId: Int): Single<List<PickupData>> =
        myChildApi
            .getPickup(teacherId, childId)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun addPickup(pickupData: PickupData): Single<DefaultResponse> =
        myChildApi.addPickup(pickupData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}