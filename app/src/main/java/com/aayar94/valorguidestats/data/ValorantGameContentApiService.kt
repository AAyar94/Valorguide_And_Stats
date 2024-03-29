package com.aayar94.valorguidestats.data

import androidx.annotation.Keep
import com.aayar94.valorguidestats.data.models.game_content.Agent
import com.aayar94.valorguidestats.data.models.game_content.BaseModel
import com.aayar94.valorguidestats.data.models.game_content.Bundles
import com.aayar94.valorguidestats.data.models.game_content.LevelBorders
import com.aayar94.valorguidestats.data.models.game_content.Season
import com.aayar94.valorguidestats.data.models.game_content.Spray
import com.aayar94.valorguidestats.data.models.game_content.Tiers
import com.aayar94.valorguidestats.data.models.game_content.ValorantMap
import com.aayar94.valorguidestats.data.models.game_content.Weapon
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface ValorantGameContentApiService {

    @GET("/v1/agents")
    suspend fun agents(@Query("language") string: String): BaseModel<List<Agent>>

    @GET("/v1/competitivetiers")
    suspend fun competitiveTiers(@Query("language") string: String): BaseModel<List<Tiers>>

    @GET("/v1/maps")
    suspend fun maps(@Query("language") string: String): BaseModel<List<ValorantMap>>

    @GET("/v1/seasons")
    suspend fun seasons(@Query("language") string: String): BaseModel<List<Season>>

    @GET("/v1/weapons")
    suspend fun weapons(@Query("language") string: String): BaseModel<List<Weapon>>

    @GET("/v1/bundles")
    suspend fun bundles(@Query("language") string: String): BaseModel<List<Bundles>>

    @GET("/v1/levelborders")
    suspend fun getLevelBorder(): BaseModel<List<LevelBorders>>

    @GET("/v1/sprays")
    suspend fun getSprays(): BaseModel<List<Spray>>
}