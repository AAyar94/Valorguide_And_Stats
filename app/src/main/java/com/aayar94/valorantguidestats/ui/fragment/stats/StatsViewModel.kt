package com.aayar94.valorantguidestats.ui.fragment.stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.Tiers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var tiersList = MutableLiveData<Array<Tiers>?>()

    fun getTiers() {
        viewModelScope.launch {
            tiersList.run { postValue(repository.competitiveTiers().data[4].tiers.toArray() as Array<Tiers>?) }
        }
    }

}

