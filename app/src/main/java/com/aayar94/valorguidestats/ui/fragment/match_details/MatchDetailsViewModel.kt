package com.aayar94.valorguidestats.ui.fragment.match_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorguidestats.data.Repository
import com.aayar94.valorguidestats.data.models.user_stats.match_details.UserMatchDetailDataModel
import com.aayar94.valorguidestats.util.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var matchDetails = MutableLiveData<ResponseHandler<UserMatchDetailDataModel>>()

    fun getMatchDetails(matchId: String) {
        viewModelScope.launch {
            val matchDetailResponse = repository.getUserMatchDetails(matchId)
            matchDetails.postValue(matchDetailResponse)
        }
    }

}