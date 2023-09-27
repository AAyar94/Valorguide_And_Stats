package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.data.models.game_content.BaseModel
import com.aayar94.valorantguidestats.data.models.game_content.Bundles
import com.aayar94.valorantguidestats.data.models.game_content.Season
import com.aayar94.valorantguidestats.data.models.game_content.TierDetail
import com.aayar94.valorantguidestats.data.models.game_content.ValorantMap
import com.aayar94.valorantguidestats.data.models.game_content.Weapon
import com.aayar94.valorantguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_mmr_change.UserMMRChangeDataModel
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import com.aayar94.valorantguidestats.util.Constants.Companion.SYSTEM_LANG_CODE
import com.aayar94.valorantguidestats.util.ResponseHandler
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

    suspend fun getAgents(query: String = langCode): BaseModel<List<Agent>> {
        return remoteDataSource.getAgents(query)
    }

    suspend fun competitiveTiers(query: String = langCode): BaseModel<List<TierDetail>> {
        return remoteDataSource.competitiveTiers(query)
    }

    suspend fun getMaps(query: String = langCode): BaseModel<List<ValorantMap>> {
        return remoteDataSource.getMaps(query)
    }

    suspend fun getSeasons(query: String = langCode): BaseModel<List<Season>> {
        return remoteDataSource.getSeasons(query)
    }

    suspend fun getWeapons(query: String = langCode): BaseModel<List<Weapon>> {
        return remoteDataSource.getWeapons(query)
    }

    suspend fun getBundles(query: String = langCode): BaseModel<List<Bundles>> {
        return remoteDataSource.getBundles(query)
    }

    suspend fun getUserMainStats(
        gameTag: String,
        tagCode: String
    ): ResponseHandler<UserStatsMainDataModel> {
        return remoteDataSource.getUserMainStats(gameTag, tagCode)
    }

    suspend fun getUserMatchHistory(
        region: String,
        puuid: String
    ): ResponseHandler<UserMatchesDataModel> {
        return remoteDataSource.getUserMatchHistory(region, puuid)
    }

    suspend fun getUserMMRChange(
        region: String,
        puuid: String
    ): ResponseHandler<UserMMRChangeDataModel> {
        return remoteDataSource.getUserMMRChange(region, puuid)
    }

    suspend fun getServerStatus(
        region: String
    ): ResponseHandler<ServerStatusDataModel> {
        return remoteDataSource.getServerStatus(region)
    }

    suspend fun getUserMatchDetails(
        matchId: String
    ): ResponseHandler<UserMatchDetailDataModel> {
        return remoteDataSource.getMatchDetail(matchId)
    }

}