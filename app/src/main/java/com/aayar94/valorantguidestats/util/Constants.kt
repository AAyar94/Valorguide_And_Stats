package com.aayar94.valorantguidestats.util

import java.util.Locale

class Constants {
    companion object{
        /** RETROFIT */
        val BASE_URL = "https://valorant-api.com/v1/";

        val SYSTEM_LANG_CODE = Locale.getDefault().toLanguageTag()
    }
}