package com.aayar94.valorantguidestats.ui.fragment.your_stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorantguidestats.data.models.user_stats.user_cards.UserStatsMainDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourStatsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val userMainStats = MutableLiveData<UserStatsMainDataModel>()
    val userMatchHistory = MutableLiveData<UserMatchesDataModel?>()

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
}