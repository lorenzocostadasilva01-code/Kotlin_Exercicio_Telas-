package com.example.exercicio_telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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

// Modelo de Dados para os itens do menu
data class MenuItem(
    val title: String,
    val price: String,
    val imageRes: Int
)

@Composable
fun RestaurantMenuScreen(
    onNavigateToCart: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFFFFFE4)
    val cardBackgroundColor = Color(0xFFECECE3)
    val badgeBackgroundColor = Color(0xFFE2E2D6)
    val goldAccent = Color(0xFFD4AF37)
    val bottomNavBg = Color(0xFF333333)

    val items = listOf(
        MenuItem("Minestrone gourmet traveller", "120,00", R.drawable.imagem1),
        MenuItem("Rigatoni alla matriciana", "95,00", R.drawable.imagem2),
        MenuItem("RAR Pinot noir", "80,00", R.drawable.imagem3)
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
                        onClick = onNavigateToSearch,
                        modifier = Modifier.background(goldAccent, CircleShape)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = Color.Black)
                    }
                    IconButton(
                        // AQUI OCORRE A NAVEGAÇÃO PARA O CARRINHO
                        onClick = onNavigateToCart,
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items) { item ->
                    MenuItemCard(item, cardBackgroundColor, badgeBackgroundColor)
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(item: MenuItem, cardBg: Color, badgeBg: Color) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(16.dp)).background(Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(badgeBg).padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = item.title, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF4A4A4A))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(badgeBg).padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.price, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A4A4A))
                }
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(Color.Black)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Adicionar", tint = Color(0xFFD4AF37))
                }
            }
        }
    }
}