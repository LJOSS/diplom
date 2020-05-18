package com.example.my_child.presentation.teacher.classlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_class_list.*

class ClassListFragment : BaseFragment() {

    companion object {
        fun newInstance(): Fragment =
            ClassListFragment()

        const val ClassListFragment_TAG = "ClassListFragment_TAG"
    }

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

        children_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ClassListAdapter(requireContext(), arrayListOf())
        }
    }
}