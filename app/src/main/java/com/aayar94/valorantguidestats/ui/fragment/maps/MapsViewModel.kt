package com.aayar94.valorantguidestats.ui.fragment.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.game_content.ValorantMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    var _mapList = MutableLiveData<Array<ValorantMap>?>()
    fun getMaps() {
        viewModelScope.launch {
            _mapList.postValue(repository.getMaps().data)
        }

    }
}