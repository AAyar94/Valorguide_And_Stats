package com.aayar94.valorantguidestats.ui.fragment.stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.models.TierDetail
import com.aayar94.valorantguidestats.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var tiersList = MutableLiveData<ArrayList<TierDetail>?>()

    fun getTiers() {
        viewModelScope.launch {
            for (i in 0 until repository.competitiveTiers().data[0].tiers.size) {
                tiersList.postValue(repository.competitiveTiers().data[0].tiers)
            }
        }
    }
}

