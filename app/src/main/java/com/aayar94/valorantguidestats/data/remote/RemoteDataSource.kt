package com.aayar94.valorantguidestats.data.remote

import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.ValorantApiService
import com.aayar94.valorantguidestats.data.ValorantUserStatsAPI
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val valorantApiService: ValorantApiService,
    private val valorantUserStatsAPI: ValorantUserStatsAPI
) {
    suspend fun getAgents(query: String): BaseModel<Array<Agent>> {
        return valorantApiService.agents(query)
    }

    suspend fun competitiveTiers(query: String): BaseModel<Array<Tiers>> {
        return valorantApiService.competitiveTiers(query)
    }

    suspend fun getMaps(query: String): BaseModel<Array<ValorantMap>> {
        return valorantApiService.maps(query)
    }

    suspend fun getSeasons(query: String): BaseModel<Array<Season>> {
        return valorantApiService.seasons(query)
    }

    suspend fun getWeapons(query: String): BaseModel<Array<Weapon>> {
        return valorantApiService.weapons(query)
    }

    suspend fun getUserMainStats(gameTag: String, tagCode: String): UserStatsMainDataModel {
        return valorantUserStatsAPI.getUserStatsMain(gameTag, tagCode)
    }

    suspend fun getUserMatchHistory(region: String, puuid: String): UserMatchesDataModel? {
        return valorantUserStatsAPI.getUserLifetimeMatches(region, puuid)
    }
}