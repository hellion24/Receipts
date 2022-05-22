package ru.mirea.sipirecipes.utility

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun View.bindVisibility(visible: Boolean) {
    isVisible = visible == true
}