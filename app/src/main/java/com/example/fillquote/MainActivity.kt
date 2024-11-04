package com.example.fillquote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fillquote.ui.theme.FillQuoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FillQuoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmQuote(
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FilmQuote(modifier: Modifier = Modifier) {
    data class Film(
        var quote: Int,
        var film: Int,
        var image: Int,
        var backgroundColor: Int
    )

    val quotes = listOf(
        Film(R.string.quote1, R.string.film1, R.drawable.image1, R.color.teal_200),
        Film(R.string.quote2, R.string.film2, R.drawable.image2, R.color.green),
        Film(R.string.quote3, R.string.film3, R.drawable.image3, R.color.orange),
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    val currentQuote = stringResource(quotes[currentIndex].quote)
    val currentFilm = stringResource(quotes[currentIndex].film)
    val currentImage = painterResource(quotes[currentIndex].image)
    val currentBackground = colorResource(quotes[currentIndex].backgroundColor)


    Box(
        modifier = modifier
            .background(currentBackground)
            .padding(32.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = currentImage,
                contentDescription = "Film image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 4.dp)
                    .padding(top = 12.dp)
            )
            Text(
                text = currentQuote,
                fontSize = 44.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = currentFilm,
                fontSize = 32.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(32.dp)
            )
            Button(onClick = {
                currentIndex = (currentIndex + 1) % quotes.size
            }) {
                Text(text = stringResource(R.string.button_label))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilmQuotePreview() {
    FillQuoteTheme {
        FilmQuote()
    }
}
