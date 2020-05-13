package com.example.my_child.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.my_child.BuildConfig
import com.example.my_child.R
import com.example.my_child.data.api.dto.data.MedicineData
import com.example.my_child.presentation.signup.SignUpViewModel
import com.example.my_child.presentation.signup.SignUpViewModelFactory
import com.example.my_child.utils.debugLog
import com.example.my_child.utils.getImageBody
import com.squareup.picasso.Picasso
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_welcome.*
import okhttp3.MultipartBody

class TestActivity : AppCompatActivity() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private var bool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val viewModel =
            ViewModelProvider(this, SignUpViewModelFactory())
                .get(SignUpViewModel::class.java)

        insertMedicine.setOnClickListener { getMedicine(viewModel) }
        loadImage()
    }

    private fun insertPhoto(viewModel: SignUpViewModel) {
        val q = PickImageDialog.build(PickSetup())
        q.setOnPickResult {
            debugLog(it.toString())
            val body: MultipartBody.Part = getImageBody(this, it.path, "image")
            debugLog(body.toString())
            disposable.add(
                viewModel
                    .addPhoto(body, System.currentTimeMillis().toString())
                    .subscribe({
                        debugLog(it.toString())
                    }, Throwable::printStackTrace)
            )
        }.show(supportFragmentManager)
    }

    private fun loadImage() {
        Picasso
            .with(this)
            .load(BuildConfig.MY_CHILD_HOST_PHOTOS + "test.png")
            .into(imageView)
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