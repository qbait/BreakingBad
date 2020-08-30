package io.digitalheart.breakingbad.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.digitalheart.breakingbad.R

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
}

fun ConstraintLayout.setSelectableBackground() {
    val selectableBackground = findViewById<View>(R.id.selectableBackground)
    if (selectableBackground == null) context.inflater.inflate(R.layout.view_selectable, this)
}

val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }