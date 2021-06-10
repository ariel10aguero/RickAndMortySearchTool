package com.example.rickandmortyapi.data.model


data class CharacterResponse(
        val info: Info,
        val results: ArrayList<Character>
)


data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?,
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: HashMap<String,String>,
    val location: HashMap<String,String>,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
