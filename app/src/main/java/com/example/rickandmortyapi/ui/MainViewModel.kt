package com.example.rickandmortyapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.domain.MainRepository
import com.example.rickandmortyapi.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MainRepository) : ViewModel() {

    private val _characterState: MutableLiveData<DataState<CharacterResponse>> = MutableLiveData()
    val characterState: LiveData<DataState<CharacterResponse>>
        get() = _characterState

    private val _locationState: MutableLiveData<DataState<LocationResponse>> = MutableLiveData()
    val locationState: LiveData<DataState<LocationResponse>>
        get() = _locationState

    private val _episodeState: MutableLiveData<DataState<EpisodeResponse>> = MutableLiveData()
    val episodeState: LiveData<DataState<EpisodeResponse>>
        get() = _episodeState



    fun getCharacter(name: String? = null, status: String? = null, species: String? = null, type: String? = null, gender: String? = null) {
        viewModelScope.launch {
            repo.getCharacter(name, status, species, type, gender).onEach {character ->
                _characterState.value = character
            }.launchIn(viewModelScope)
        }
    }

    fun getLocation(name: String? = null, type: String? = null, dimension: String? = null){
        viewModelScope.launch {
            repo.getLocation(name, type, dimension).onEach { location ->
                _locationState.value = location
            }.launchIn(viewModelScope)
        }
    }

    fun getEpisode(name: String? = null, episode: String? = null){
        viewModelScope.launch {
            repo.getEpisode(name, episode).onEach { episode ->
                _episodeState.value = episode
            }.launchIn(viewModelScope)
        }
    }

}