package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.TierWrapper
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import com.aayar94.valorantguidestats.util.Constants.Companion.SYSTEM_LANG_CODE
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    val langCode = SYSTEM_LANG_CODE
    suspend fun getAgents(query: String = langCode): BaseModel<Array<Agent>> {
        return remoteDataSource.getAgents(query)
    }

    suspend fun competitiveTiers(query: String = langCode): BaseModel<Array<TierWrapper>> {
        return remoteDataSource.competitiveTiers(query)
    }

    suspend fun getGameModes(query: String = langCode): BaseModel<Array<Gamemode>> {
        return remoteDataSource.getGameModes(query)
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

}