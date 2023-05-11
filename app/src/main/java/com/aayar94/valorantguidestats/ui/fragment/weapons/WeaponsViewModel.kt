package com.aayar94.valorantguidestats.ui.fragment.weapons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.game_content.Weapon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val weaponList = MutableLiveData<Array<Weapon>?>()

    fun getWeapons() {
        viewModelScope.launch {
            val response = repository.getWeapons().data
            weaponList.postValue(response)
        }
    }

}