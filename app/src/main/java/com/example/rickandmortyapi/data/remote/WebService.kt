package com.example.rickandmortyapi.data.remote

import com.example.rickandmortyapi.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {

   @GET("character/")
   suspend fun getCharacter(
           @Query ("name") name: String?,
           @Query("status") status: String?,
           @Query("species") species: String?,
           @Query("type") type: String?,
           @Query("gender") gender: String?,

   ): CharacterResponse


}