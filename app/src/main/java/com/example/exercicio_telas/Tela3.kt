package com.example.exercicio_telas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private enum class CardBrand {
    VISA,
    MASTERCARD
}

@Composable
fun PaymentScreen(
    totalAmount: String,
    onNavigateToMenu: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onAddNewMethod: () -> Unit = {}
) {
    val backgroundColor = Color(0xFFFFFFE4)
    val cardBackgroundColor = Color(0xFFECECE3)
    val badgeBackgroundColor = Color(0xFFE2E2D6)
    val goldAccent = Color(0xFFD4AF37)
    val bottomNavBg = Color(0xFF333333)
    val textColor = Color(0xFF5A5A5A)

    var selectedCard by remember { mutableStateOf(CardBrand.VISA) }

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            PaymentBottomBar(
                backgroundColor = bottomNavBg,
                accentColor = goldAccent,
                onNavigateToMenu = onNavigateToMenu,
                onNavigateToCart = onNavigateToCart
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PaymentHeader(goldAccent = goldAccent)

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Formas de pagamento",
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Cartão Visa
            PaymentMethodCard(
                brand = CardBrand.VISA,
                cardNumber = "**** **** 2222",
                isSelected = selectedCard == CardBrand.VISA,
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                goldAccent = goldAccent,
                onClick = { selectedCard = CardBrand.VISA }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Cartão Mastercard
            PaymentMethodCard(
                brand = CardBrand.MASTERCARD,
                cardNumber = "**** **** 5555",
                isSelected = selectedCard == CardBrand.MASTERCARD,
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                goldAccent = goldAccent,
                onClick = { selectedCard = CardBrand.MASTERCARD }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botão/Card para Adicionar Novo Cartão
            AddPaymentMethodCard(
                borderColor = goldAccent,
                textColor = textColor,
                onClick = onAddNewMethod
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Exibição do Total vindo do Carrinho
            TotalToPayCard(
                amount = totalAmount,
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PaymentHeader(goldAccent: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(1.dp, goldAccent.copy(alpha = 0.55f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "♢", color = goldAccent, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(goldAccent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                tint = Color.White,
                modifier = Modifier.size(23.dp)
            )
        }
    }
}

@Composable
private fun PaymentMethodCard(
    brand: CardBrand,
    cardNumber: String,
    isSelected: Boolean,
    cardBackground: Color,
    badgeBackground: Color,
    goldAccent: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardBackground)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) goldAccent else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (brand) {
            CardBrand.VISA -> Text(
                text = "VISA",
                color = Color(0xFF1A1F71),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
            CardBrand.MASTERCARD -> MastercardLogo()
        }

        Spacer(modifier = Modifier.width(22.dp))

        Text(
            text = cardNumber,
            modifier = Modifier.weight(1f),
            color = Color(0xFF5A5A5A),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(
            onClick = { /* Ação de editar cartão */ },
            modifier = Modifier
                .size(36.dp)
                .background(badgeBackground, RoundedCornerShape(10.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar cartão",
                tint = Color(0xFF333333),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun MastercardLogo() {
    Box(
        modifier = Modifier.width(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(Color(0xFFEB001B), CircleShape)
        )
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(22.dp)
                .background(Color(0xFFF79E1B).copy(alpha = 0.85f), CircleShape)
        )
    }
}

@Composable
private fun AddPaymentMethodCard(
    borderColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = borderColor.copy(alpha = 0.8f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(22.dp.toPx()),
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar",
                tint = Color(0xFF222222),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Adicionar novo método",
                color = textColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun TotalToPayCard(
    amount: String,
    cardBackground: Color,
    badgeBackground: Color,
    textColor: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(cardBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Total a Pagar",
                color = Color(0xFF8A8A80),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(badgeBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = amount,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun PaymentBottomBar(
    backgroundColor: Color,
    accentColor: Color,
    onNavigateToMenu: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Surface(color = backgroundColor, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaymentNavButton(Icons.Default.Home, "Início", accentColor, onNavigateToMenu)
            PaymentNavButton(Icons.Default.Search, "Buscar", accentColor, {})
            PaymentNavButton(Icons.Default.ShoppingCart, "Carrinho", accentColor, onNavigateToCart)
        }
    }
}

@Composable
private fun PaymentNavButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    description: String,
    accentColor: Color,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(42.dp)
            .background(accentColor, CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Preview
@Composable
fun PaymentScreenPreview() {
    MaterialTheme {
        PaymentScreen(totalAmount = "R$ 494,00")
    }
}