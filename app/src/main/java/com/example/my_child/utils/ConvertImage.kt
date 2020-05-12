package com.example.my_child.utils

import android.app.Activity
import id.zelory.compressor.Compressor
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException

fun getImageBody(
    activity: Activity,
    path: String,
    name: String
): MultipartBody.Part {
    val compressedImageFile: File =
        getCompressedImageFile(activity, path)
    val requestFile =
        RequestBody.create(MediaType.parse("multipart/form-data"), compressedImageFile)
    return MultipartBody.Part.createFormData(name, compressedImageFile.name, requestFile)
}

fun getCompressedImageFile(
    activity: Activity,
    imagePath: String
): File {
    val imageFile = File(imagePath)
    val compressedImageFile: File
    try {
        compressedImageFile = Compressor(activity).compressToFile(imageFile)
        return compressedImageFile
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return imageFile
}
