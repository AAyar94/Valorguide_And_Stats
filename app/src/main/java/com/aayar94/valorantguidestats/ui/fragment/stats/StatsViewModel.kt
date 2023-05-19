package com.aayar94.valorantguidestats.ui.fragment.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.game_content.TierDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private var _tiersList = MutableLiveData<List<TierDetail>?>()
    val tiersList: LiveData<List<TierDetail>?> = _tiersList

    fun getTiers() {
        viewModelScope.launch {
            _tiersList.postValue(repository.competitiveTiers().data)
        }
    }
}

