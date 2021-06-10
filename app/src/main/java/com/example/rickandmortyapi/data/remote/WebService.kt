package com.example.rickandmortyapi.data.remote

import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
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

   @GET("location/")
   suspend fun getLocation(
           @Query ("name") name: String?,
           @Query("type") type: String?,
           @Query("dimension") dimension: String?,

   ): LocationResponse

   @GET("episode/")
   suspend fun getEpisode(
       @Query("name") name: String?,
       @Query("episode") episode: String?
   ): EpisodeResponse


}