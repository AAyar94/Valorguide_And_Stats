package com.aayar94.valorantguidestats.data.remote

import com.aayar94.valorantguidestats.data.ValorantApiService
import com.aayar94.valorantguidestats.data.ValorantUserStatsAPI
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Season
import com.aayar94.valorantguidestats.data.models.Tiers
import com.aayar94.valorantguidestats.data.models.ValorantMap
import com.aayar94.valorantguidestats.data.models.Weapon
import com.aayar94.valorantguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_mmr_change.UserMMRChangeDataModel
import com.aayar94.valorantguidestats.util.ResponseHandler
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

    suspend fun getUserMainStats(
        gameTag: String,
        tagCode: String
    ): ResponseHandler<UserStatsMainDataModel> {
        val response = try {
            valorantUserStatsAPI.getUserStatsMain(gameTag, tagCode)
        } catch (e: Exception) {
            return ResponseHandler.Error("An Error Occurred")
        }
        return ResponseHandler.Success(response)
    }

    suspend fun getUserMatchHistory(
        region: String,
        puuid: String
    ): ResponseHandler<UserMatchesDataModel> {
        val userMatchHistoryResponse = try {
            valorantUserStatsAPI.getUserLifetimeMatches(region, puuid)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(userMatchHistoryResponse)
    }

    suspend fun getUserMMRChange(
        region: String,
        puuid: String
    ): ResponseHandler<UserMMRChangeDataModel> {
        val mmrChangeResponse = try {
            valorantUserStatsAPI.getUserMMR(region, puuid)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(mmrChangeResponse)
    }

    suspend fun getServerStatus(
        region: String
    ): ResponseHandler<ServerStatusDataModel> {
        val serverStatusResponse = try {
            valorantUserStatsAPI.valorantServerStatus(region)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(serverStatusResponse)
    }

    suspend fun getMatchDetail(
        matchId: String
    ): ResponseHandler<UserMatchDetailDataModel> {
        val matchDetailResponse = try {
            valorantUserStatsAPI.getMatchDetail(matchId)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(matchDetailResponse)
    }
}