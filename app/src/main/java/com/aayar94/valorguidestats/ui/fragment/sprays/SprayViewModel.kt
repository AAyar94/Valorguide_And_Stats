package com.aayar94.valorguidestats.ui.fragment.sprays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorguidestats.data.Repository
import com.aayar94.valorguidestats.data.models.game_content.Spray
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SprayViewModel @Inject constructor(
    val repository: Repository,
) : ViewModel() {

    private val _sprays = MutableLiveData<List<Spray>?>()
    val sprays: LiveData<List<Spray>?> = _sprays
    fun getSprays() {
        viewModelScope.launch {
            _sprays.postValue(repository.getSprays().data)
        }
    }
}