package com.aayar94.valorantguidestats.ui.fragment.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class InfoViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    var mapImage: MutableLiveData<String> = MutableLiveData()
    var weaponImage: MutableLiveData<String> = MutableLiveData()
    var statImage: MutableLiveData<String> = MutableLiveData()

    fun getStatBackground() {
        viewModelScope.launch {
            val randomNumber = Random.nextInt(0, repository.getMaps().data.size)
            val response = repository.competitiveTiers().data[0].tiers[randomNumber].largeIcon
            statImageSetter(response)
        }
    }

    fun getMapBackground() {
        viewModelScope.launch {
            repository.getMaps()
            val randomNumber = Random.nextInt(0, repository.getMaps().data.size)
            val mapBackground = repository.getMaps().data[randomNumber].splash
            mapImageSetter(mapBackground)
        }
    }

    fun getWeaponBackground() {
        viewModelScope.launch {
            val weaponResponse = repository.getWeapons().data
            val randomWeaponNumber = Random.nextInt(0, weaponResponse.size)
            val weaponBackground = weaponResponse[randomWeaponNumber].displayIcon
            weaponImageSetter(weaponBackground)
        }
    }

    private fun weaponImageSetter(weaponBackground: String) {
        if (weaponBackground.isEmpty()) {
            getWeaponBackground()
        } else {
            weaponImage.postValue(weaponBackground)
        }
    }

    private fun mapImageSetter(string: String) {
        if (string.isEmpty()) {
            getMapBackground()
        } else {
            mapImage.postValue(string)
        }
    }

    private fun statImageSetter(string: String?) {
        if (string?.isEmpty() == true) {
            getStatBackground()
        } else {
            statImage.postValue(string!!)
        }
    }
}