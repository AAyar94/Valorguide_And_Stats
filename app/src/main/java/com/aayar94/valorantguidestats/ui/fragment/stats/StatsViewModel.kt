package com.aayar94.valorantguidestats.ui.fragment.stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.TierDetail
import com.aayar94.valorantguidestats.data.Repository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var tiersList = MutableLiveData<Array<TierDetail>?>()

    fun getTiers() {
        viewModelScope.launch {
            val stringObject = repository.competitiveTiers().data[4].tiers
            val gson = GsonBuilder().create()
            /*val theList = gson.fromJson<ArrayList<String>>(stringObject, object :
                TypeToken<ArrayList<String>>() {}.type)

            tiersList.postValue(theList)*/
        }
    }

}

