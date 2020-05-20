package com.example.my_child.presentation.fragments

import androidx.fragment.app.Fragment
import com.example.my_child.utils.Constants
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    protected val userId: Int by lazy { requireArguments().getInt(Constants.USER_ID) }

    protected val teacherId: Int by lazy { requireArguments().getInt(Constants.TEACHER_ID) }

    protected val childId: Int by lazy { requireArguments().getInt(Constants.CHILD_ID) }

    protected val fragmentType: Int by lazy { requireArguments().getInt(Constants.TYPE_FRAGMENT) }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}