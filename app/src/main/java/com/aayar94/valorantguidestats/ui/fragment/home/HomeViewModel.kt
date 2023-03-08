package com.aayar94.valorantguidestats.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Weapon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var _agents = MutableLiveData<Array<Agent>?>()
    var _gameMode = MutableLiveData<Array<Gamemode>?>()
    var _weapons = MutableLiveData<Array<Weapon>?>()

    fun getAgents() {
        viewModelScope.launch {
            _agents.postValue(repository.getAgents().data)
        }
    }

    fun getGameMode() {
        viewModelScope.launch {
            _gameMode.postValue(repository.getGameModes().data)
        }
    }

    fun getWeapons() {
        viewModelScope.launch {
            _weapons.postValue(repository.getWeapons().data)
        }
    }
}

