package com.example.my_child.presentation.teacher.classlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.presentation.BaseFragment
import com.example.my_child.presentation.teacher.classlist.ChildInfoActivity.Companion.CHILD_INFO
import com.example.my_child.utils.Constants.USER_ID
import kotlinx.android.synthetic.main.fragment_class_list.*

class ClassListFragment : BaseFragment() {

    companion object {
        fun newInstance(userId: Int): Fragment =
            ClassListFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userId)
                }
            }

        const val ClassListFragment_TAG = "ClassListFragment_TAG"
    }

    private val userId: Int by lazy { requireArguments().getInt(USER_ID) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_class_list, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, ClassListViewModelFactory())
            .get(ClassListViewModel::class.java)

        initList(viewModel)
    }

    private fun initList(viewModel: ClassListViewModel) {
        disposable.add(
            viewModel
                .getClassList(userId)
                .subscribe({
                    children_list.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = ClassListAdapter(requireContext(), it.data) {
                            openChildInfo(it)
                        }
                    }
                }, Throwable::printStackTrace)
        )
    }

    private fun openChildInfo(dataResponse: ParentDataResponse) {
        startActivity(
            Intent(
                requireActivity(),
                ChildInfoActivity::class.java
            ).apply {
                putExtra(CHILD_INFO, dataResponse)
            })
    }
}