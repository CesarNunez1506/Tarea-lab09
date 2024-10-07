package com.example.tarea09.model

data class PokemonSpecies(
    val name: String,
    val url: String
) {
    val imageUrl: String
        get() {
            val id = url.trimEnd('/').split("/").last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }
}
