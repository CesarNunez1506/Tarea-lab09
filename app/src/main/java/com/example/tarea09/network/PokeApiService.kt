package com.example.tarea09.network

import com.example.tarea09.model.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon-species/")
    suspend fun getPokemonSpecies(): Response<PokemonSpeciesList>
}

data class PokemonSpeciesList(
    val results: List<PokemonSpecies>
)
