package com.example.tarea09.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea09.model.PokemonSpecies
import com.example.tarea09.network.ApiClient
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    var pokemonList by mutableStateOf<List<PokemonSpecies>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    // Lista filtrada basada en la consulta de búsqueda
    val filteredPokemonList: List<PokemonSpecies>
        get() = if (searchQuery.isEmpty()) {
            pokemonList
        } else {
            pokemonList.filter {
                it.name.contains(searchQuery.trim(), ignoreCase = true)
            }
        }

    init {
        getPokemonSpecies()
    }

    private fun getPokemonSpecies() {
        viewModelScope.launch {
            try {
                val response = ApiClient.pokeApiService.getPokemonSpecies()
                if (response.isSuccessful) {
                    response.body()?.let {
                        pokemonList = it.results
                        isLoading = false
                    } ?: run {
                        errorMessage = "No se encontraron datos."
                        isLoading = false
                    }
                } else {
                    errorMessage = "Error: ${response.code()}"
                    isLoading = false
                }
            } catch (e: Exception) {
                errorMessage = "Excepción: ${e.message}"
                isLoading = false
            }
        }
    }

    // Función para actualizar la consulta de búsqueda
    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
}
