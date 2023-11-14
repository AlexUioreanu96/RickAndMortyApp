package com.example.fashionday.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.fashionday.R
import com.example.fashionday.ui.theme.LocalDimensions

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(id: String, viewModel: DetailsViewModel = hiltViewModel()) {

    val dimensions = LocalDimensions.current

    val uiState by viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.getCharacterById(id.toInt())

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x6F206D0E)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        GlideImage(
            model = uiState.character?.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = uiState.character?.name ?: "",
            color = Color.White,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 35.sp,
        )

        Row(
            modifier = Modifier.padding(LocalDimensions.current.spaceSm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.green_dot),
                contentDescription = "",
                tint = when (uiState.character?.status) {
                    "Alive" -> Color.Green
                    "Dead" -> Color.Red
                    else -> Color.White
                }
            )
            Text(
                text = uiState.character?.status ?: "",
                modifier = Modifier.padding(start = dimensions.spaceSm),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, end = 45.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = uiState.character?.species ?: "",
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 35.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    text = "Species",
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.ExtraLight,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 20.sp,
                )

            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = uiState.character?.gender ?: "",
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 35.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    text = "Gender",
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.ExtraLight,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 20.sp,
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 32.dp),
            text = uiState.character?.origin ?: "",
            color = Color.White,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 35.sp,
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            text = "Origin",
            color = Color.White,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.ExtraLight,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 23.sp,
        )
    }
}