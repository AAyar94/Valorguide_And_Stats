package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import retrofit2.http.GET
import retrofit2.http.Path

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
    )

    @GET("valorant/v1/by-puuid/mmr/{region}/{puuid}")
    suspend fun getUserMMR(
        @Path("region") region: String,
        @Path("puuid") puuid: String
    )

    @GET("valorant/v1/status/{region}")
    suspend fun valorantServerStatus(
        @Path("region") region: String
    )

}