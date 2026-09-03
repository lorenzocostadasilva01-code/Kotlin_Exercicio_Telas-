package com.example.exercicio_telas

import androidx.compose.runtime.*

@Composable
fun AppNavigator() {
    // ATENÇÃO AQUI: Mudamos de 'val' para 'var'
    var currentScreen by remember { mutableStateOf("tela_carrinho") }

    when (currentScreen) {
        "tela_menu" -> {
            RestaurantMenuScreen(
                onNavigateToCart = { currentScreen = "tela_carrinho" },
                onNavigateToSearch = { /* Lógica de busca futura */ }
            )
        }
        "tela_carrinho" -> {
            CartScreen(
                onBackToMenu = { currentScreen = "tela_menu" }
            )
        }
    }
}