package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import com.aayar94.valorantguidestats.util.Constants.Companion.SYSTEM_LANG_CODE
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    private val localLangCode = SYSTEM_LANG_CODE
    private val langCode = when (localLangCode) {
        "ar-AE" -> "ar-AE"
        "de-DE" -> "de-DE"
        "en-US" -> "en-US"
        "es-ES" -> "es-ES"
        "es-MX" -> "es-MX"
        "fr-FR" -> "fr-FR"
        "id-ID" -> "id-ID"
        "it-IT" -> "it-IT"
        "ja-JP" -> "ja-JP"
        "ko-KR" -> "ko-KR"
        "pl-PL" -> "pl-PL"
        "pt-BR" -> "pt-PR"
        "ru-RU" -> "ru-RU"
        "th-TH" -> "th-TH"
        "tr-TR" -> "tr-TR"
        "vi-VN" -> "vi-VN"
        "zn-CH" -> "zn-CH"
        "zn-TW" -> "zn-TW"
        else -> "en-US"
    }

    suspend fun getAgents(query: String = langCode): BaseModel<Array<Agent>> {
        return remoteDataSource.getAgents(query)
    }

    suspend fun competitiveTiers(query: String = langCode): BaseModel<Array<Tiers>> {
        return remoteDataSource.competitiveTiers(query)
    }

    suspend fun getMaps(query: String = langCode): BaseModel<Array<ValorantMap>> {
        return remoteDataSource.getMaps(query)
    }

    suspend fun getSeasons(query: String = langCode): BaseModel<Array<Season>> {
        return remoteDataSource.getSeasons(query)
    }

    suspend fun getWeapons(query: String = langCode): BaseModel<Array<Weapon>> {
        return remoteDataSource.getWeapons(query)
    }

}