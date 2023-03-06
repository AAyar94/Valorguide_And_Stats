package com.aayar94.valorantguidestats.data.remote

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.ContentTier
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.ValorantApiService
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val valorantApiService: ValorantApiService
) {
    suspend fun getAgents(): Call<BaseModel<Array<Agent>>> {
        return valorantApiService.agents()
    }

    suspend fun getContentTiers(): Call<BaseModel<Array<ContentTier>>> {
        return valorantApiService.contentTiers()
    }

    suspend fun getGameModes(): Call<BaseModel<Array<Gamemode>>> {
        return valorantApiService.gamemodes()
    }

    suspend fun getMaps(): Call<BaseModel<Array<ValorantMap>>> {
        return valorantApiService.maps()
    }

    suspend fun getSeasons(): Call<BaseModel<Array<Season>>> {
        return valorantApiService.seasons()
    }

    suspend fun getWeapons(): Call<BaseModel<Array<Weapon>>> {
        return valorantApiService.weapons()
    }
}