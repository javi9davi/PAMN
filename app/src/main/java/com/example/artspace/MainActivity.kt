package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtDisplay()
            }
        }
    }
}

data class ArtPiece(
    val imageResId: Int,
    val title: String,
    val description: String,
    val author: String,
    val creationDate: String
)

val artPieces = listOf(
    ArtPiece(R.drawable.elnacimientodevenus, "El nacimiento de Venus", "La obra maestra de Sandro Botticelli que representa a la diosa Venus.", "Sandro Botticelli", "1484-1486"),
    ArtPiece(R.drawable.lacreaciondeadan, "La creación de Adán", "El fresco icónico de Miguel Ángel del techo de la Capilla Sixtina.", "Miguel Ángel", "1512"),
    ArtPiece(R.drawable.lasmeninas, "Las Meninas", "La compleja y enigmática pintura de Diego Velázquez sobre la familia real española.", "Diego Velázquez", "1656"),
    ArtPiece(R.drawable.nenufares, "Nenúfares", "Una serie de pinturas impresionistas de Claude Monet que representan su jardín de nenúfares.", "Claude Monet", "1897-1926"),
    ArtPiece(R.drawable.monalisa, "La Mona Lisa", "El famoso retrato de Lisa Gherardini pintado por Leonardo da Vinci.", "Leonardo da Vinci", "1503-1506"),
    ArtPiece(R.drawable.lanocheestrellada, "La noche estrellada", "La representación arremolinada y expresiva del cielo nocturno de Vincent van Gogh.", "Vincent van Gogh", "1889")
)

@Composable
fun gradientText() = Brush.linearGradient(
    colors = listOf(
        Color(0xFF020024),
        Color(0xFF420979)
    )
)

@Composable
fun ArtItem(artPiece: ArtPiece) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(max = 400.dp)
            .background(color = Color.White)
            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
            .padding(2.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(artPiece.imageResId),
                contentDescription = artPiece.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = artPiece.title,
                style = TextStyle(fontSize = 20.sp, color = Color.Black),
                modifier = Modifier.padding(top = 8.dp),
            )
            Text(
                text = artPiece.description,
                style = TextStyle(fontSize = 16.sp, color = Color.DarkGray, textAlign = TextAlign.Justify),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = "${artPiece.author} (${artPiece.creationDate})",
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun ArtDisplay() {
    var currentIndex by remember { mutableStateOf(0) }
    val currentArtPiece = artPieces[currentIndex]

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = "Icono de la aplicación",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 8.dp, start = 13.dp)
        )

        Text(
            text = "ArtList",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                brush = gradientText(),
                letterSpacing = 1.5.sp
            )
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ArtItem(currentArtPiece)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(onClick = {
                // Navegar a la pieza anterior si no es la primera
                currentIndex = if (currentIndex > 0) currentIndex - 1 else artPieces.size - 1
            }) {
                Text("Previous")
            }
            Button(onClick = {
                // Navegar a la pieza siguiente si no es la última
                currentIndex = if (currentIndex < artPieces.size - 1) currentIndex + 1 else 0
            }) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArtDisplay() {
    ArtSpaceTheme {
        ArtDisplay()
    }
}
