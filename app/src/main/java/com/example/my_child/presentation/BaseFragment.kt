package com.example.my_child.presentation

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}