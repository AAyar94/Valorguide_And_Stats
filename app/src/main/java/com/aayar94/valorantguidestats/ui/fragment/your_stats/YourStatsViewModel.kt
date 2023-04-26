package com.aayar94.valorantguidestats.ui.fragment.your_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YourStatsViewModel @Inject constructor(
    val repository: Repository
):ViewModel(){

    /**    Need Response Model Class      */

    fun getUserStats(userId:String){
        viewModelScope.launch {
            /** Need Request  */
        }
    }

}