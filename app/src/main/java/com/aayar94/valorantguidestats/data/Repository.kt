package com.aayar94.valorantguidestats.data

import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.ContentTier
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.remote.RemoteDataSource
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getAgents(): Call<BaseModel<Array<Agent>>> {
        return remoteDataSource.getAgents()
    }

    suspend fun getContextTiers(): Call<BaseModel<Array<ContentTier>>> {
        return remoteDataSource.getContentTiers()
    }

    suspend fun getGameModes(): Call<BaseModel<Array<Gamemode>>> {
        return remoteDataSource.getGameModes()
    }

    suspend fun getMaps(): Call<BaseModel<Array<ValorantMap>>> {
        return remoteDataSource.getMaps()
    }

    suspend fun getSeasons(): Call<BaseModel<Array<Season>>> {
        return remoteDataSource.getSeasons()
    }

    suspend fun getWeapons(): Call<BaseModel<Array<Weapon>>> {
        return remoteDataSource.getWeapons()
    }

}