package com.example.exercicio_telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    CartScreen()
                }
            }
        }
    }
}

// Modelo de Dados apenas para renderização visual
data class CartItem(
    val title: String,
    val quantity: Int,
    val price: String,
    val imageRes: Int
)
@Preview
@Composable
fun CartScreen() {
    val backgroundColor = Color(0xFFFFFFE4)
    val cardBackgroundColor = Color(0xFFECECE3)
    val badgeBackgroundColor = Color(0xFFE2E2D6)
    val goldAccent = Color(0xFFD4AF37)
    val bottomNavBg = Color(0xFF333333)

    val items = listOf(
        CartItem("Minestrone", 2, "240,00", R.drawable.imagem1),
        CartItem("Rigatoni", 3, "174,00", R.drawable.imagem2),
        CartItem("RAR Pinot noir", 1, "80,00", R.drawable.imagem3)
    )

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars,
        bottomBar = {
            Surface(
                color = bottomNavBg,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .height(64.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.background(goldAccent, CircleShape)
                    ) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Black)
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.background(goldAccent, CircleShape)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.Black)
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.background(goldAccent, CircleShape)
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho", tint = Color.Black)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(padding)
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(44.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(goldAccent, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Itens do Carrinho
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items) { item ->
                    CartItemCard(item, cardBackgroundColor, badgeBackgroundColor)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Total
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(cardBackgroundColor)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Total: 494,00",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A4A)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
// Botão Concluir Pagamento
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = cardBackgroundColor)
            ) {
                Text(
                    text = "Concluir pagamento",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A4A)
                )
            }
        }
    }
}
@Composable
fun CartItemCard(item: CartItem, cardBg: Color, badgeBg: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(cardBg)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
// Imagem do Produto
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                color = Color(0xFF5A5A5A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bloco Quantidade
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(badgeBg)
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "QTDA", fontSize = 10.sp, color = Color.Gray)
                    Text(text = "${item.quantity}", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                // Bloco Preço
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(badgeBg)
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.price,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A4A4A)
                    )
                }
            }
        }
    }
}