package com.aayar94.valorantguidestats.data.models

import retrofit2.http.GET

interface ValorantApiService {

    @GET("/v1/agents")
    suspend fun agents(SYSTEM_LANG: String): BaseModel<Array<Agent>>

    @GET("/v1/contenttiers?language=tr-TR")
    suspend fun contentTiers(): BaseModel<Array<ContentTier>>

    @GET("/v1/gamemodes?language=tr-TR")
    suspend fun gamemodes(): BaseModel<Array<Gamemode>>

    @GET("/v1/maps?language=tr-TR")
    suspend fun maps(): BaseModel<Array<ValorantMap>>

    @GET("/v1/seasons?language=tr-TR")
    suspend fun seasons(): BaseModel<Array<Season>>

    @GET("/v1/weapons?language=tr-TR")
    suspend fun weapons(): BaseModel<Array<Weapon>>

}