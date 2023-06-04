package com.aayar94.valorantguidestats.ui.fragment.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aayar94.valorantguidestats.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class InfoViewModel @Inject constructor(
    val repository: Repository,
) : ViewModel() {

    var mapImage: MutableLiveData<String> = MutableLiveData()
    var weaponImage: MutableLiveData<String> = MutableLiveData()
    var statImage: MutableLiveData<String?> = MutableLiveData()
    var sprayImage: MutableLiveData<String?> = MutableLiveData()

    fun getStatBackground() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val statsResponse = repository.competitiveTiers().data
            while (true) {
                val randomStatsNumber = Random.nextInt(0, statsResponse.size)
                val statsBackground = statsResponse[randomStatsNumber].largeIcon
                statImageSetter(statsBackground)
                delay(5000)
            }
        }
    }

    fun getMapBackground() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val mapResponse = repository.getMaps().data
            while (true) {
                val randomMapNumber = Random.nextInt(0, mapResponse.size)
                val mapBackground = mapResponse[randomMapNumber].splash
                mapImageSetter(mapBackground)
                delay(5000)
            }
        }
    }

    fun getWeaponBackground() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val weaponResponse = repository.getWeapons().data
            while (true) {
                val randomWeaponNumber = Random.nextInt(0, weaponResponse.size)
                val weaponBackground = weaponResponse[randomWeaponNumber].displayIcon
                weaponImageSetter(weaponBackground)
                delay(5000)
            }
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
        if (string.isNullOrEmpty()) {
            getStatBackground()
        } else {
            statImage.postValue(string)
        }
    }

    fun getSprayBackground() {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val sprayResponse = repository.getSprays().data
            while (true) {
                val randomSprayNumber = Random.nextInt(0, sprayResponse.size)
                val sprayBackground = sprayResponse[randomSprayNumber].displayIcon
                sprayImage.postValue(sprayBackground)
                delay(5000)
            }
        }
    }
}