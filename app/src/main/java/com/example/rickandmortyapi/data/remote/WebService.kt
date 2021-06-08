package com.example.rickandmortyapi.data.remote

import com.example.rickandmortyapi.data.model.Character
import retrofit2.http.GET


interface WebService {

    @GET
    fun getCharacter(): Character

}