package com.aayar94.valorantguidestats.ui.fragment.agents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.Agent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var _agents = MutableLiveData<Array<Agent>?>()


    fun getAgents() {
        viewModelScope.launch {
            _agents.postValue(repository.getAgents().data)
        }
    }


}