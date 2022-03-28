package com.example.rickandmortyapi.domain

import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import com.example.rickandmortyapi.util.DataState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacter(query: Map<String, String>): Flow<DataState<CharacterResponse>>


    suspend fun getLocation(query: Map<String, String>): Flow<DataState<LocationResponse>>


    suspend fun getEpisode(query: Map<String, String>): Flow<DataState<EpisodeResponse>>



}