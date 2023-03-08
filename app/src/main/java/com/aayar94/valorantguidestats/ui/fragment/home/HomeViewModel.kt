package com.aayar94.valorantguidestats.ui.fragment.home

import android.util.Log
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var _agents = MutableLiveData<Array<Agent>?>()
    var _gameMode = MutableLiveData<BaseModel<Array<Gamemode>>>()
    var _weapons = MutableLiveData<BaseModel<Array<Weapon>>>()

    fun getAgents() {
        viewModelScope.launch {
            val call = repository.getAgents()
            call.enqueue(object : Callback<BaseModel<Array<Agent>>> {
                override fun onResponse(
                    call: Call<BaseModel<Array<Agent>>>,
                    response: Response<BaseModel<Array<Agent>>>
                ) {
                    _agents.postValue(response.body()?.data)
                }

                override fun onFailure(call: Call<BaseModel<Array<Agent>>>, t: Throwable) {
                    Log.e("Agents Request Error", t.message.toString())
                }

            })


        }
    }

    fun getGameMode() {
        viewModelScope.launch {
            //repository.getGameModes().enqueue()
        }
    }

    fun getWeapons() {
        viewModelScope.launch {
            //repository.getWeapons().enqueue()
        }
    }

}