package com.example.exercicio_telas

import androidx.compose.runtime.*

@Composable
fun AppNavigator() {
    // Guarda a tela atual (iniciando em "pagamento" ou "menu")
    var currentScreen by remember { mutableStateOf("pagamento") }

    // Guarda o valor total do carrinho para passar para a tela de pagamento
    var totalAmount by remember { mutableStateOf("R$ 0,00") }

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
                onNavigateToPayment = { valorCalculado ->
                    // Salva o valor retornado do carrinho e navega para pagamento
                    totalAmount = valorCalculado
                    currentScreen = "pagamento"
                }
            )
        }
        "pagamento" -> {
            PaymentScreen(
                totalAmount = totalAmount, // Passa o valor recebido do carrinho
                onNavigateToMenu = { currentScreen = "menu" },
                onNavigateToCart = { currentScreen = "carrinho" }
            )
        }
    }
}