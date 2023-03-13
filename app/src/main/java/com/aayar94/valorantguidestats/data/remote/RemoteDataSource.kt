package com.aayar94.valorantguidestats.data.remote

import com.aayar94.valorantguidestats.Tiers
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.ValorantApiService
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val valorantApiService: ValorantApiService
) {
    suspend fun getAgents(query : String): BaseModel<Array<Agent>> {
        return valorantApiService.agents(query)
    }

    suspend fun competitiveTiers(query : String): BaseModel<Array<Tiers>> {
        return valorantApiService.competitiveTiers(query)
    }

    suspend fun getGameModes(query : String): BaseModel<Array<Gamemode>> {
        return valorantApiService.gamemodes(query)
    }

    suspend fun getMaps(query : String): BaseModel<Array<ValorantMap>> {
        return valorantApiService.maps(query)
    }

    suspend fun getSeasons(query : String): BaseModel<Array<Season>> {
        return valorantApiService.seasons(query)
    }

    suspend fun getWeapons(query : String): BaseModel<Array<Weapon>> {
        return valorantApiService.weapons(query)
    }
}