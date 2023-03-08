package com.aayar94.valorantguidestats.data.models

import retrofit2.Call
import retrofit2.http.GET

interface ValorantApiService {

    @GET("/v1/agents")
    fun agents(): Call<BaseModel<Array<Agent>>>

    @GET("/v1/contenttiers")
    suspend fun contentTiers(): Call<BaseModel<Array<ContentTier>>>

    @GET("/v1/gamemodes")
    suspend fun gamemodes(): Call<BaseModel<Array<Gamemode>>>

    @GET("/v1/maps")
    suspend fun maps(): Call<BaseModel<Array<ValorantMap>>>

    @GET("/v1/seasons")
    suspend fun seasons(): Call<BaseModel<Array<Season>>>

    @GET("/v1/weapons")
    fun weapons(): Call<BaseModel<Array<Weapon>>>

}