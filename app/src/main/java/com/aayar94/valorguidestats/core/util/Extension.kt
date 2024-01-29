package com.aayar94.valorguidestats.core.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun GlideImageLoader(context: Context, src: String, view: ImageView) {
    Glide.with(context)
        .load(src)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}