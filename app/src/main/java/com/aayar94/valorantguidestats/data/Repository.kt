package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.TierWrapper
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getAgents(): BaseModel<Array<Agent>> {
        return remoteDataSource.getAgents()
    }

    suspend fun competitiveTiers(): BaseModel<Array<TierWrapper>> {
        return remoteDataSource.competitiveTiers()
    }

    suspend fun getGameModes(): BaseModel<Array<Gamemode>> {
        return remoteDataSource.getGameModes()
    }

    suspend fun getMaps(): BaseModel<Array<ValorantMap>> {
        return remoteDataSource.getMaps()
    }

    suspend fun getSeasons(): BaseModel<Array<Season>> {
        return remoteDataSource.getSeasons()
    }

    suspend fun getWeapons(): BaseModel<Array<Weapon>> {
        return remoteDataSource.getWeapons()
    }

}