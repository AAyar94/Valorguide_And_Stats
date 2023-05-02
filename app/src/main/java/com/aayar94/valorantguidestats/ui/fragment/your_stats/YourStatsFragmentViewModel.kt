package com.aayar94.valorantguidestats.ui.fragment.your_stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorantguidestats.util.ResponseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourStatsFragmentViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val serverStatus = MutableLiveData<ResponseHandler<ServerStatusDataModel>>()

    fun getServerStatus(region: String) {
        viewModelScope.launch {
            val response = repository.getServerStatus(region)
            serverStatus.postValue(response)
        }

    }

}