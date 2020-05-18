package com.example.my_child.presentation.fragments

import androidx.fragment.app.Fragment
import com.example.my_child.utils.Constants
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    protected val userId: Int by lazy { requireArguments().getInt(Constants.USER_ID) }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}