package com.aayar94.valorantguidestats.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Locale

class Constants {
    companion object {
        /** RETROFIT */
        val BASE_URL = "https://valorant-api.com/v1/";

        val SYSTEM_LANG_CODE = Locale.getDefault().toLanguageTag()

        val VALORANT_URL = "https://playvalorant.com"

        val USER_STATS_BASE_URL="https://api.henrikdev.xyz/"

        fun GlideImageLoader(context: Context, src: String, view: ImageView) {
            Glide.with(context)
                .load(src)
                .into(view)
        }
    }
}