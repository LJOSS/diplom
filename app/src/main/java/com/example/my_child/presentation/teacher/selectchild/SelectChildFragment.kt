package com.example.my_child.presentation.teacher.selectchild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_child.R
import com.example.my_child.data.api.dto.response.ParentDataResponse
import com.example.my_child.presentation.fragments.BaseFragment
import com.example.my_child.presentation.parent.absent.ParentAbsentFragment
import com.example.my_child.presentation.parent.absent.ParentAbsentFragment.Companion.ParentAbsentFragment_TAG
import com.example.my_child.presentation.teacher.TeacherHomeActivity
import com.example.my_child.presentation.teacher.absent.TeacherAbsentFragment
import com.example.my_child.presentation.teacher.absent.TeacherAbsentFragment.Companion.TeacherAbsentFragment_TAG
import com.example.my_child.presentation.teacher.chat.ChatActivity
import com.example.my_child.presentation.teacher.diary.DiaryFragment
import com.example.my_child.presentation.teacher.diary.DiaryFragment.Companion.DiaryFragment_TAG
import com.example.my_child.presentation.teacher.medicine.TeacherMedicineFragment
import com.example.my_child.presentation.teacher.medicine.TeacherMedicineFragment.Companion.TeacherMedicineFragment_TAG
import com.example.my_child.presentation.teacher.pickup.TeacherPickupFragment
import com.example.my_child.presentation.teacher.pickup.TeacherPickupFragment.Companion.TeacherPickupFragment_TAG
import com.example.my_child.utils.Constants
import com.example.my_child.utils.Constants.ABSENT_FRAGMENT
import com.example.my_child.utils.Constants.CHAT_FRAGMENT
import com.example.my_child.utils.Constants.DIARY_FRAGMENT
import com.example.my_child.utils.Constants.MEDICINE_FRAGMENT
import com.example.my_child.utils.Constants.PICKUP_FRAGMENT
import com.example.my_child.utils.GridSpacingItemDecoration
import com.example.my_child.utils.dpToPx
import kotlinx.android.synthetic.main.select_child_fragment.*

class SelectChildFragment : BaseFragment() {

    companion object {
        fun newInstance(teacherId: Int, type: Int): SelectChildFragment =
            SelectChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TEACHER_ID, teacherId)
                    putInt(Constants.TYPE_FRAGMENT, type)
                }
            }

        const val SelectChildFragment_TAG = "SelectChildFragment_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.select_child_fragment, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, SelectChildViewModelFactory())
            .get(SelectChildViewModel::class.java)

        disposable.add(
            viewModel
                .getClassList(teacherId)
                .subscribe({
                    child_list.apply {
                        layoutManager = GridLayoutManager(requireContext(), 3)
                        itemAnimator = DefaultItemAnimator()
                        addItemDecoration(
                            GridSpacingItemDecoration(
                                3,
                                dpToPx(3),
                                true,
                                requireActivity()
                            )
                        )
                        adapter = SelectChildAdapter(requireContext(), it.data) {
                            openChildInfo(it)
                        }
                    }
                }, Throwable::printStackTrace)
        )

    }

    private fun openChildInfo(childInfo: ParentDataResponse) {
        when (fragmentType) {
            CHAT_FRAGMENT -> {
                startActivity(
                    ChatActivity.newInstance(
                        teacherId,
                        childInfo.childId,
                        requireActivity()
                    )
                )
            }
            DIARY_FRAGMENT -> {
                openFragment(
                    DiaryFragment.newInstance(teacherId, childInfo.childId),
                    DiaryFragment_TAG
                )
            }
            MEDICINE_FRAGMENT -> {
                openFragment(
                    TeacherMedicineFragment.newInstance(teacherId, childInfo.childId),
                    TeacherMedicineFragment_TAG
                )
            }
            ABSENT_FRAGMENT -> {
                openFragment(
                    TeacherAbsentFragment.newInstance(teacherId, childInfo.childId),
                    TeacherAbsentFragment_TAG
                )
            }
            PICKUP_FRAGMENT -> {
                openFragment(
                    TeacherPickupFragment.newInstance(teacherId, childInfo.childId),
                    TeacherPickupFragment_TAG
                )
            }
        }
    }

    private fun openFragment(fragment: BaseFragment, tag: String) {
        (activity as TeacherHomeActivity).openFragmentFromActivity(fragment, tag)
    }

    override fun onResume() {
        super.onResume()
        var title = ""
        when (fragmentType) {
            CHAT_FRAGMENT -> { title = "Чат" }
            DIARY_FRAGMENT -> { title = "Дневник" }
            MEDICINE_FRAGMENT -> { title = "Таблетки" }
            ABSENT_FRAGMENT -> { title = "Отсутствия" }
            PICKUP_FRAGMENT ->  { title = "Увоз" }
        }

        (activity as TeacherHomeActivity).setTitle(title)
    }
}