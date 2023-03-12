package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.TierWrapper
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantApiService {

    @GET("/v1/agents")
    suspend fun agents(@Query("language") string: String): BaseModel<Array<Agent>>

    @GET("/v1/competitivetiers")
    suspend fun competitiveTiers(@Query("language") string: String): BaseModel<Array<TierWrapper>>

    @GET("/v1/gamemodes")
    suspend fun gamemodes(@Query("language") string: String): BaseModel<Array<Gamemode>>

    @GET("/v1/maps")
    suspend fun maps(@Query("language") string: String): BaseModel<Array<ValorantMap>>

    @GET("/v1/seasons")
    suspend fun seasons(@Query("language") string: String): BaseModel<Array<Season>>

    @GET("/v1/weapons")
    suspend fun weapons(@Query("language") string: String): BaseModel<Array<Weapon>>

}