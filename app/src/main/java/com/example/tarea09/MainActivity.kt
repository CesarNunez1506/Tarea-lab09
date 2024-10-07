package com.example.tarea09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme.colorScheme
import com.example.tarea09.ui.PokemonListScreen
import com.example.tarea09.ui.theme.MyPokemonAppTheme
import androidx.compose.ui.Modifier // Este es el import correcto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPokemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    PokemonListScreen()
                }
            }
        }
    }
}
