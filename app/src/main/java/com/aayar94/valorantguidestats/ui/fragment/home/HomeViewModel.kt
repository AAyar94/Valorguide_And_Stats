package com.aayar94.valorantguidestats.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.data.models.BaseModel
import com.aayar94.valorantguidestats.data.models.Gamemode
import com.aayar94.valorantguidestats.data.models.Weapon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _agents = MutableLiveData<BaseModel<Array<Agent>>>()
    private var _gameMode = MutableLiveData<BaseModel<Array<Gamemode>>>()
    private var _weapons = MutableLiveData<BaseModel<Array<Weapon>>>()

    suspend fun getAgents() {
        viewModelScope.launch {
            //repository.getAgents().enqueue()
        }
    }

    suspend fun getGameMode() {
        viewModelScope.launch {
            //repository.getGameModes().enqueue()
        }
    }

    suspend fun getWeapons() {
        viewModelScope.launch {
            //repository.getWeapons().enqueue()
        }
    }

}