package com.example.my_child.presentation.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.utils.debugLog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var bool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val viewModel =
            ViewModelProvider(this, SignUpViewModelFactory())
                .get(SignUpViewModel::class.java)

        insertMedicine.setOnClickListener { getMedicine(viewModel) }

    }

    private fun getMedicine(viewModel: SignUpViewModel) {
        if (bool) {
            disposable.add(
                viewModel.insertMedicine(
                    MedicineData(
                        idTeacher = 1,
                        idParent = 1,
                        date = "12-05-2020",
                        message = "ПИЛЮЛЯ",
                        subtext = "ПИЛЮЛИТЬ"
                    )
                ).subscribe({
                    medicine.text = it.toString()
                    debugLog(it.toString())
                }, Throwable::printStackTrace)
            )
        } else {
            disposable.add(
                viewModel.getMedicine().subscribe({ list ->
                    var textmedicine = ""
                    list.forEach {
                        textmedicine += "$it \n"
                    }
                    medicine.text = textmedicine
                }, Throwable::printStackTrace)
            )
        }
        bool = !bool
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
