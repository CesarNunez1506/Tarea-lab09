package com.example.tarea09.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import com.example.tarea09.ui.components.PokemonListItem
import com.example.tarea09.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel = viewModel()) {
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val searchQuery by viewModel::searchQuery
    val filteredPokemonList = viewModel.filteredPokemonList

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    viewModel.updateSearchQuery(query)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = { Text("Buscar Pokémon") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar Icono") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Puedes manejar la acción de búsqueda aquí si es necesario
                    }
                )
            )

            when {
                isLoading -> {
                    // Indicador de carga
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    // Mensaje de error
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                else -> {
                    // Lista de Pokémon filtrada
                    LazyColumn {
                        items(filteredPokemonList.size) { index ->
                            PokemonListItem(pokemon = filteredPokemonList[index])
                        }
                    }
                }
            }
        }
    }
}