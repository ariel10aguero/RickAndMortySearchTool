package com.example.rickandmortyapi.data.remote

import com.example.rickandmortyapi.data.model.CharacterResponse
import com.example.rickandmortyapi.data.model.EpisodeResponse
import com.example.rickandmortyapi.data.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface WebService {

   @GET("character/")
   suspend fun getCharacter(@QueryMap query: Map<String, String>
   ): CharacterResponse

   @GET("location/")
   suspend fun getLocation(@QueryMap query: Map<String, String>
   ): LocationResponse

   @GET("episode/")
   suspend fun getEpisode(@QueryMap query: Map<String, String>
   ): EpisodeResponse


}
//
//@Query ("name") name: String?,
//@Query("status") status: String?,
//@Query("species") species: String?,
//@Query("type") type: String?,
//@Query("gender") gender: String?