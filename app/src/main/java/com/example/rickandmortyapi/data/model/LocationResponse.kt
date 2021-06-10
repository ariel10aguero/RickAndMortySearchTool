package com.example.rickandmortyapi.data.model

data class LocationResponse(
    val info: Info,
    val results: ArrayList<Location>
)

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)
