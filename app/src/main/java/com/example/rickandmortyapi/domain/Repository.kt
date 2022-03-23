package com.example.rickandmortyapi.domain

import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.util.DataState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacter(name: String?,
                             status: String?,
                             species: String?,
                             type: String?,
                             gender: String?): Flow<DataState<CharacterResponse>>


    suspend fun getLocation(name: String?,
                            type: String?,
                            dimension: String?): Flow<DataState<LocationResponse>>


    suspend fun getEpisode(name: String?, episode: String?): Flow<DataState<EpisodeResponse>>



}