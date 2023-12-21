package com.aayar94.valorantguidestats.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.util.Locale

class Constants {
    companion object {
        /** RETROFIT */
        val BASE_URL = "https://valorant-api.com/v1/";

        val SYSTEM_LANG_CODE = Locale.getDefault().toLanguageTag()

        val VALORANT_URL = "https://playvalorant.com"

        val USER_STATS_BASE_URL = "https://api.henrikdev.xyz/"

        fun GlideImageLoader(context: Context, src: String, view: ImageView) {
            Glide.with(context)
                .load(src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }


        /**     Server List     */
        const val SERVER_EUROPE = "Europe(EU)"
        const val SERVER_ASIA = "Asia(AP)"
        const val SERVER_NA = "North America(USA)"
        const val SERVER_KOREA =  "Korea(KR)"
        const val SERVER_LATAM ="Latam(Mexico,Santiago,Miami)"
        const val SERVER_SAO_PAULO ="Sao Paulo(BR)"





    }
}