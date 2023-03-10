package com.aayar94.valorantguidestats.data.models

import retrofit2.http.GET

interface ValorantApiService {

    @GET("/v1/agents")
    suspend fun agents(): BaseModel<Array<Agent>>

    @GET("/v1/contenttiers")
    suspend fun contentTiers(): BaseModel<Array<ContentTier>>

    @GET("/v1/gamemodes")
    suspend fun gamemodes(): BaseModel<Array<Gamemode>>

    @GET("/v1/maps")
    suspend fun maps(): BaseModel<Array<ValorantMap>>

    @GET("/v1/seasons")
    suspend fun seasons(): BaseModel<Array<Season>>

    @GET("/v1/weapons")
    suspend fun weapons(): BaseModel<Array<Weapon>>

}