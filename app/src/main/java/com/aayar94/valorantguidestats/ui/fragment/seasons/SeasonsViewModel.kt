package com.aayar94.valorantguidestats.ui.fragment.seasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.game_content.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private var _seasonList = MutableLiveData<List<Season>?>()
    val seasonList: LiveData<List<Season>?> = _seasonList

    fun getSeasons() {
        viewModelScope.launch {
            _seasonList.postValue(repository.getSeasons().data)
        }
    }
}