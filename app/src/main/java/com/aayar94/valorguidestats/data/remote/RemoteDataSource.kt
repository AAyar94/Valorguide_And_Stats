package com.aayar94.valorguidestats.data.remote

import com.aayar94.valorguidestats.data.ValorantGameContentApiService
import com.aayar94.valorguidestats.data.ValorantUserStatsAPI
import com.aayar94.valorguidestats.data.models.game_content.Agent
import com.aayar94.valorguidestats.data.models.game_content.BaseModel
import com.aayar94.valorguidestats.data.models.game_content.Bundles
import com.aayar94.valorguidestats.data.models.game_content.LevelBorders
import com.aayar94.valorguidestats.data.models.game_content.Season
import com.aayar94.valorguidestats.data.models.game_content.Spray
import com.aayar94.valorguidestats.data.models.game_content.TierDetail
import com.aayar94.valorguidestats.data.models.game_content.ValorantMap
import com.aayar94.valorguidestats.data.models.game_content.Weapon
import com.aayar94.valorguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_mmr_change.UserMMRChangeDataModel
import com.aayar94.valorguidestats.util.ResponseHandler
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val valorantGameContentApiService: ValorantGameContentApiService,
    private val valorantUserStatsAPI: ValorantUserStatsAPI,
) {
    suspend fun getAgents(query: String): BaseModel<List<Agent>> {
        val result = valorantGameContentApiService.agents(query)
        val filteredList = result.data.filter {
            it.uuid != "ded3520f-4264-bfed-162d-b080e2abccf9"
        }
        return BaseModel(200, filteredList)
    }

    suspend fun competitiveTiers(query: String): BaseModel<List<TierDetail>> {
        val result = valorantGameContentApiService.competitiveTiers(query)
        val tierResult = result.data.last()
        val formattedList = tierResult.tiers.filter { it.tier != 1 && it.tier != 2 }
        return BaseModel(200, formattedList)
    }

    suspend fun getMaps(query: String): BaseModel<List<ValorantMap>> {
        return valorantGameContentApiService.maps(query)
    }

    suspend fun getSeasons(query: String): BaseModel<List<Season>> {
        return valorantGameContentApiService.seasons(query)
    }

    suspend fun getWeapons(query: String): BaseModel<List<Weapon>> {
        return valorantGameContentApiService.weapons(query)
    }

    suspend fun getBundles(query: String): BaseModel<List<Bundles>> {
        return valorantGameContentApiService.bundles(query)
    }

    /**     USER BASED REQUEST  */
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
        puuid: String,
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
        puuid: String,
    ): ResponseHandler<UserMMRChangeDataModel> {
        val mmrChangeResponse = try {
            valorantUserStatsAPI.getUserMMR(region, puuid)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(mmrChangeResponse)
    }

    suspend fun getServerStatus(
        region: String,
    ): ResponseHandler<ServerStatusDataModel> {
        val serverStatusResponse = try {
            valorantUserStatsAPI.valorantServerStatus(region)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(serverStatusResponse)
    }

    suspend fun getMatchDetail(
        matchId: String,
    ): ResponseHandler<UserMatchDetailDataModel> {
        val matchDetailResponse = try {
            valorantUserStatsAPI.getMatchDetail(matchId)
        } catch (e: Exception) {
            return ResponseHandler.Error(e.message)
        }
        return ResponseHandler.Success(matchDetailResponse)
    }

    suspend fun getLevelBorders(): BaseModel<List<LevelBorders>> {
        return valorantGameContentApiService.getLevelBorder()
    }

    suspend fun getSprays(): BaseModel<List<Spray>> {
        return valorantGameContentApiService.getSprays()
    }
}