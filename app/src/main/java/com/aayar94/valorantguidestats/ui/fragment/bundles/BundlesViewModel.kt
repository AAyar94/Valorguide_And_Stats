package com.aayar94.valorantguidestats.ui.fragment.bundles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aayar94.valorantguidestats.data.Repository
import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.data.models.game_content.Bundles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BundlesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _bundles = MutableLiveData<List<Bundles>?>()
    val bundles: LiveData<List<Bundles>?> = _bundles

    fun getBundles() {
        viewModelScope.launch(Dispatchers.IO) {
            _bundles.postValue(repository.getBundles().data)
        }
    }
}