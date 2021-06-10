package com.example.rickandmortyapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.model.CharacterResponse
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

    fun getCharacter(name: String? = null, status: String? = null, species: String? = null, type: String? = null, gender: String? = null) {
        viewModelScope.launch {
            repo.getCharacter(name, status, species, type, gender).onEach {character ->
                _characterState.value = character
            }.launchIn(viewModelScope)
        }
    }

}