package com.example.mymoviecatalogue.utils

import android.content.Context
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

object DrawablesUtils {
    fun changeItemMenuTint(context: Context, item: MenuItem, @ColorRes color: Int) {
        val drawable = item.icon
        val wrapDrawable = drawable?.let { DrawableCompat.wrap(it) }
        if (wrapDrawable != null) {
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context, color))
        }
        item.setIcon(wrapDrawable)
    }
}