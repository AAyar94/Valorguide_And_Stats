package com.aayar94.valorguidestats.ui.fragment.your_stats_preview

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorguidestats.data.Repository
import com.aayar94.valorguidestats.data.models.game_content.LevelBorders
import com.aayar94.valorguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import com.aayar94.valorguidestats.data.models.user_stats.user_mmr_change.UserMMRChangeDataModel
import com.aayar94.valorguidestats.core.util.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourStatsPreviewViewModel @Inject constructor(
    val repository: Repository,
) : ViewModel() {

    val userMainStats = MutableLiveData<ResponseHandler<UserStatsMainDataModel>>()
    val userMatchHistory = MutableLiveData<ResponseHandler<UserMatchesDataModel>>()
    val userMMRChange = MutableLiveData<ResponseHandler<UserMMRChangeDataModel>>()
    val levelBorders = MutableLiveData<List<LevelBorders>?>()

    fun getUserStats(gamerTag: String, tag: String) {
        viewModelScope.launch {
            val response = repository.getUserMainStats(gamerTag, tag)
            userMainStats.postValue(response)
        }
    }

    fun getUserMatchHistory(region: String, puuid: String) {
        viewModelScope.launch {
            val matchHistoryResponse = repository.getUserMatchHistory(region, puuid)
            userMatchHistory.postValue(matchHistoryResponse)
        }
    }

    fun getUserMMRChange(region: String, puuid: String) {
        viewModelScope.launch {
            val mmrChangeResponse = repository.getUserMMRChange(region, puuid)
            userMMRChange.postValue(mmrChangeResponse)
        }
    }

    fun getLevelBorders() {
        viewModelScope.launch {
            val mLevelBorders = repository.getLevelBorder()
            levelBorders.postValue(mLevelBorders.data)
        }
    }

    fun deleteUserGamerTagAndTag(context: Context) {
        val sharedPref =
            context.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val sharedPrefEditor = sharedPref?.edit()
        sharedPrefEditor?.putString("gamerTag", "")
        sharedPrefEditor?.putString("tag", "")
        sharedPrefEditor?.apply()
    }
}