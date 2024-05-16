package com.example.foodapp.TypeConverters

import android.content.Context
import android.net.Uri
import java.io.IOException

fun convertUriToByteArray(context: Context, uri: Uri): ByteArray? {
    return try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.buffered().readBytes()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}