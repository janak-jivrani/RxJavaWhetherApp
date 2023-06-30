package com.example.weatherappassignment.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherappassignment.R
import java.text.SimpleDateFormat
import java.util.Calendar

fun Activity.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    if (msg.trim().isNotEmpty()) {
        Toast.makeText(this, msg, duration).show()
    }
}

fun ImageView.loadImage(src: String?) {
    Glide.with(this).load(src).transition(DrawableTransitionOptions.withCrossFade()).into(this)
}

fun Activity.showAlert(title: String, msg: String) {
    val alertDialog = AlertDialog.Builder(this).setTitle(title).setMessage(msg).setPositiveButton(
        "Ok"
    ) { dialog, which -> }.create()
    alertDialog.show()
}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (isMarshmallow23Greater()) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}

fun Context.isMarshmallow23Greater(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

fun Context.getBgColors(position: Int): Int {
    return when (position) {
        0 -> ContextCompat.getColor(this, R.color.bgColor1)
        1 -> ContextCompat.getColor(this, R.color.bgColor2)
        2 -> ContextCompat.getColor(this, R.color.bgColor3)
        3 -> ContextCompat.getColor(this, R.color.bgColor4)
        4 -> ContextCompat.getColor(this, R.color.bgColor5)
        else -> ContextCompat.getColor(this, R.color.bgColor4)
    }
}

fun Context.getNewDateFormat(date: String, currentFormat: String, newFormat: String): String {
    var generatedDate = ""
    try {
        val originalFormat = SimpleDateFormat(currentFormat)
        val targetFormat = SimpleDateFormat(newFormat)
        val newDate = originalFormat.parse(date)
        if (newDate != null) generatedDate = targetFormat.format(newDate)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("DateReview", e.toString())
    }
    return generatedDate
}

fun Context.getWeatherIconUrl(iconCode: String?): String {
    return "https://cdn.weatherbit.io/static/img/icons/$iconCode.png"
}

fun EditText.onSearch(callback: () -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_SEARCH
    maxLines = 1
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback.invoke()
            true
        }
        false
    }
}

fun View.hideKeyboard() {
    this.clearFocus()
    ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())
}

fun View.showKeyboard() {
    ViewCompat.getWindowInsetsController(this)?.show(WindowInsetsCompat.Type.ime())
}