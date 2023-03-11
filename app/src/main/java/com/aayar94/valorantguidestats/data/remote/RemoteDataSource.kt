package com.aayar94.valorantguidestats.data.remote

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.TierWrapper
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.ValorantApiService
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val valorantApiService: ValorantApiService
) {
    suspend fun getAgents(): BaseModel<Array<Agent>> {
        return valorantApiService.agents()
    }

    suspend fun competitiveTiers(): BaseModel<Array<TierWrapper>> {
        return valorantApiService.competitiveTiers()
    }

    suspend fun getGameModes(): BaseModel<Array<Gamemode>> {
        return valorantApiService.gamemodes()
    }

    suspend fun getMaps(): BaseModel<Array<ValorantMap>> {
        return valorantApiService.maps()
    }

    suspend fun getSeasons(): BaseModel<Array<Season>> {
        return valorantApiService.seasons()
    }

    suspend fun getWeapons(): BaseModel<Array<Weapon>> {
        return valorantApiService.weapons()
    }
}