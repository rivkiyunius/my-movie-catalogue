package com.example.mymoviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun ImageView.showImage(url: String) {
    Glide.with(this).load(
        "https://image.tmdb.org/t/p/w500$url"
    ).into(this)
}

fun String.dateFormat(formatPattern: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat(formatPattern, Locale.ENGLISH)
    val tz = TimeZone.getTimeZone("Asia/Jakarta")
    formatter.timeZone = tz
    return parser.parse(this)?.let { formatter.format(it) }.toString()
}