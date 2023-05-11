package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.game_content.Tiers
import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.data.models.game_content.BaseModel
import com.aayar94.valorantguidestats.data.models.game_content.Season
import com.aayar94.valorantguidestats.data.models.game_content.ValorantMap
import com.aayar94.valorantguidestats.data.models.game_content.Weapon
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantApiService {

    @GET("/v1/agents")
    suspend fun agents(@Query("language") string: String): BaseModel<Array<Agent>>

    @GET("/v1/competitivetiers")
    suspend fun competitiveTiers(@Query("language") string: String): BaseModel<Array<Tiers>>

    @GET("/v1/maps")
    suspend fun maps(@Query("language") string: String): BaseModel<Array<ValorantMap>>

    @GET("/v1/seasons")
    suspend fun seasons(@Query("language") string: String): BaseModel<Array<Season>>

    @GET("/v1/weapons")
    suspend fun weapons(@Query("language") string: String): BaseModel<Array<Weapon>>

}