package com.example.rickandmortyapi.domain

import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.remote.WebService
import com.example.rickandmortyapi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

import javax.inject.Inject

class MainRepository @Inject constructor (
    private val networkData: WebService
) : Repository {


   override suspend fun getCharacter(name: String?,
                                     status: String?,
                                     species: String?,
                                     type: String?,
                                     gender: String?): Flow<DataState<CharacterResponse>> = flow{
       emit(DataState.Loading)
       try {
           val character = networkData.getCharacter(name, status, species, type, gender)
           emit(DataState.Success(character))
       }
       catch (e: Exception){
           emit(DataState.Error(e))
       }
    }
}
