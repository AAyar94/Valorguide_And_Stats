package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import com.aayar94.valorantguidestats.util.Constants.Companion.SYSTEM_LANG_CODE
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    private val localLangCode = SYSTEM_LANG_CODE
    private val langCode = when (localLangCode) {
        "ar-AE" -> "ar-AE"      //y
        "de-DE" -> "de-DE"      //y
        "en-US" -> "en-US"      //y
        "es-ES" -> "es-ES"      //y
        "es-MX" -> "es-ES"      //y
        "fr-FR" -> "fr-FR"      //y
        //"id-ID" -> "id-ID"
        "it-IT" -> "it-IT"      //y
        "ja-JP" -> "ja-JP"      //y
        "ko-KR" -> "ko-KR"      //y
        //"pt-BR" -> "pt-PR"
        "ru-RU" -> "ru-RU"      //y
        "tr-TR" -> "tr-TR"      //y
        "zn-CH" -> "zn-CH"      //y
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

    suspend fun getUserMainStats(gameTag: String, tagCode: String): UserStatsMainDataModel {
        return remoteDataSource.getUserMainStats(gameTag,tagCode)
    }

}