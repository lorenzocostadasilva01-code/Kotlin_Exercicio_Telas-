package com.example.exercicio_telas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentScreen() {
    val backgroundColor = Color(0xFFFFFFE4)
    val cardBackgroundColor = Color(0xFFECECE3)
    val badgeBackgroundColor = Color(0xFFE2E2D6)
    val goldAccent = Color(0xFFD4AF37)
    val bottomNavBg = Color(0xFF333333)
    val textColor = Color(0xFF5A5A5A)

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            PaymentBottomBar(
                backgroundColor = bottomNavBg,
                accentColor = goldAccent
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
                text = "Payment methods",
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            PaymentMethodCard(
                brand = CardBrand.VISA,
                cardNumber = "**** **** 2222",
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            PaymentMethodCard(
                brand = CardBrand.MASTERCARD,
                cardNumber = "**** **** 2222",
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            AddPaymentMethodCard(
                borderColor = goldAccent,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(14.dp))

            BalanceCard(
                amount = "BRL 1.340,50",
                cardBackground = cardBackgroundColor,
                badgeBackground = badgeBackgroundColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private enum class CardBrand {
    VISA,
    MASTERCARD
}

@Composable
private fun PaymentHeader(goldAccent: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Substitua este bloco por painterResource(R.drawable.logo) se quiser
        // reaproveitar exatamente o logotipo usado na tela anterior.
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(1.dp, goldAccent.copy(alpha = 0.55f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "♢",
                color = goldAccent,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
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
    cardBackground: Color,
    badgeBackground: Color,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardBackground)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (brand) {
            CardBrand.VISA -> Text(
                text = "VISA",
                color = Color(0xFFB28C25),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )

            CardBrand.MASTERCARD -> MastercardLogo()
        }

        Spacer(modifier = Modifier.width(22.dp))

        Text(
            text = cardNumber,
            modifier = Modifier.weight(1f),
            color = Color(0xFFB6B6AB),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(
            onClick = {},
            modifier = Modifier
                .size(36.dp)
                .background(badgeBackground, RoundedCornerShape(10.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar cartão",
                tint = Color(0xFF333333),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun MastercardLogo() {
    Row(
        modifier = Modifier.width(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .background(Color(0xFFD7A626), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(26.dp)
                .padding(start = 9.dp)
                .background(Color(0xFFD7A626), CircleShape)
        )
    }
}

@Composable
private fun AddPaymentMethodCard(
    borderColor: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .clip(RoundedCornerShape(22.dp)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = borderColor.copy(alpha = 0.8f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(22.dp.toPx()),
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(2.dp.toPx(), 5.dp.toPx()),
                        0f
                    )
                )
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(Color.Transparent, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar método de pagamento",
                    tint = Color(0xFF222222),
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "Add New Method",
                color = textColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun BalanceCard(
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
                .height(43.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(cardBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Balance",
                color = Color(0xFFB0B0A5),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(badgeBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = amount,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun PaymentBottomBar(
    backgroundColor: Color,
    accentColor: Color
) {
    Surface(
        color = backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaymentNavButton(Icons.Default.Home, "Início", accentColor)
            PaymentNavButton(Icons.Default.Search, "Buscar", accentColor)
            PaymentNavButton(Icons.Default.ShoppingCart, "Carrinho", accentColor)
        }
    }
}

@Composable
private fun PaymentNavButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    description: String,
    accentColor: Color
) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .size(42.dp)
            .background(accentColor, CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 760)
@Composable
private fun PaymentScreenPreview() {
    MaterialTheme {
        PaymentScreen()
    }
}
