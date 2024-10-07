package com.example.tarea09.ui.components

import com.example.tarea09.model.PokemonSpecies
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.tarea09.R


@Composable
fun PokemonListItem(pokemon: PokemonSpecies) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Cargar la imagen usando Coil
            Image(
                painter = rememberImagePainter(
                    data = pokemon.imageUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.placeholder) // Asegúrate de tener una imagen de placeholder
                        error(R.drawable.error) // Asegúrate de tener una imagen de error
                    }
                ),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}