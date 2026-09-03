package com.example.exercicio_telas

import androidx.compose.runtime.*

@Composable
fun AppNavigator() {
    // Estado inicial é o menu
    var currentScreen by remember { mutableStateOf("pagamento") }

    when (currentScreen) {
        "menu" -> {
            RestaurantMenuScreen(
                onNavigateToCart = { currentScreen = "carrinho" },
                onNavigateToSearch = { /* Futuro */ }
            )
        }
        "carrinho" -> {
            CartScreen(
                onBackToMenu = { currentScreen = "menu" },
                onNavigateToPayment = { currentScreen = "pagamento" } // Vai para a tela 3
            )
        }
        "pagamento" -> {
            PaymentScreen(
                onNavigateToMenu = { currentScreen = "menu" },
                onNavigateToCart = { currentScreen = "carrinho" }
            )
        }
    }
}