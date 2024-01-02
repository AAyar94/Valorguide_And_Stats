package com.aayar94.valorguidestats.data

import androidx.annotation.Keep
import com.aayar94.valorguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_mmr_change.UserMMRChangeDataModel
import retrofit2.http.GET
import retrofit2.http.Path
@Keep
interface ValorantUserStatsAPI {

    @GET("/valorant/v1/account/{name}/{tag}")
    suspend fun getUserStatsMain(
        @Path("name") name: String,
        @Path("tag") tag: String
    ): UserStatsMainDataModel

    @GET("valorant/v1/by-puuid/lifetime/matches/{region}/{puuid}?size=10&page=1")
    suspend fun getUserLifetimeMatches(
        @Path("region") region: String,
        @Path("puuid") puuid: String
    ): UserMatchesDataModel

    @GET("valorant/v1/by-puuid/mmr/{region}/{puuid}")
    suspend fun getUserMMR(
        @Path("region") region: String,
        @Path("puuid") puuid: String
    ): UserMMRChangeDataModel

    @GET("valorant/v1/status/{region}")
    suspend fun valorantServerStatus(
        @Path("region") region: String
    ): ServerStatusDataModel

    @GET("valorant/v2/match/{matchId}")
    suspend fun getMatchDetail(
        @Path("matchId") matchId: String
    ):UserMatchDetailDataModel

}