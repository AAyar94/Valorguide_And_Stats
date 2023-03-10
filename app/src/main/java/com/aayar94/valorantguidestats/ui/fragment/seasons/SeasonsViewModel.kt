package com.aayar94.valorantguidestats.ui.fragment.seasons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var seasonList = MutableLiveData<Array<Season>?>()

    fun getSeasons() {
        viewModelScope.launch {
            seasonList.postValue(repository.getSeasons().data)

        }
    }
}