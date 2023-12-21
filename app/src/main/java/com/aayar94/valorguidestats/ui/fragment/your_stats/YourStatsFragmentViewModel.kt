package com.aayar94.valorguidestats.ui.fragment.your_stats

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorguidestats.data.Repository
import com.aayar94.valorguidestats.data.models.server_status.ServerStatusDataModel
import com.aayar94.valorguidestats.util.ResponseHandler
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

    fun saveUserEntries(context: Context, gamerTag: String, tag: String) {
        val sharedPref =
            context.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("gamerTag", gamerTag)
        editor.putString("tag", tag)
        editor.apply()
    }

    fun readUserGamerTagEntry(context: Context): String? {
        val sharedPref = context.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val gamerTag = sharedPref.getString("gamerTag", "")
        return gamerTag
    }

    fun readUserTagEntrty(context: Context): String? {
        val sharedPref = context.getSharedPreferences("valorant_preferences", Context.MODE_PRIVATE)
        val tag = sharedPref.getString("tag", "")
        return tag
    }

}