package com.example.fashionday.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.fashionday.R
import com.example.fashionday.domain.model.Character
import com.example.fashionday.ui.theme.LocalDimensions
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val colors = MaterialTheme.colorScheme
    val dimensions = LocalDimensions.current
    val typography = MaterialTheme.typography

    val uiState by viewModel.uiState

    val pullRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(dimensions.spaceMd),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensions.spaceMd),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rick_and_morty),
                    contentDescription = ""
                )
            }
        },
        containerColor = Color.Black,
        contentColor = Color.Black
    ) { contentPadding ->

        Column(modifier = Modifier.padding(contentPadding)) {
            SearchBar(uiState, viewModel)
            CharactersGrid(
                characters = uiState.filteredCharacters,
                onItemLongPressed = { characterId ->
                    viewModel.deleteItemOnLongPressed(characterId)
                },
                pullRefreshState = pullRefreshState,
                onRefresh = viewModel::fetchCharacters,
                onItemPressed = { navController.navigate("details/$it") }
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(
    uiState: CharacterUiState,
    viewModel: MainViewModel
) {
    val searchBarColor = Color(0xFF449E2F)
    OutlinedTextField(
        value = uiState.searchQuery ?: "",
        onValueChange = viewModel::onQueryChanged,
        label = { Text("Search Characters") },
        leadingIcon = {
            Icon(
                tint = searchBarColor,
                painter = painterResource(id = R.drawable.ic_character),
                contentDescription = ""
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = searchBarColor,
            focusedBorderColor = searchBarColor,
            unfocusedLabelColor = searchBarColor,
            focusedLabelColor = searchBarColor
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),
        shape = RoundedCornerShape(100)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersGrid(
    characters: List<Character>,
    onItemLongPressed: (Int) -> Unit,
    onItemPressed: (Int) -> Unit,
    pullRefreshState: SwipeRefreshState,
    onRefresh: () -> Unit,
) {
    val scrollState = rememberLazyStaggeredGridState()
    val dimensions = LocalDimensions.current

    SwipeRefresh(state = pullRefreshState, onRefresh = onRefresh) {
        LazyVerticalStaggeredGrid(
            verticalItemSpacing = dimensions.spaceSm,
            horizontalArrangement = Arrangement.spacedBy(dimensions.spaceSm),
            state = scrollState,
            columns = StaggeredGridCells.Fixed(2),
        ) {
            items(characters, key = { it.id ?: -1 }) { character ->
                CharacterItem(
                    characterName = character.name ?: "",
                    urlImage = character.image,
                    status = character.status ?: "",
                    species = character.species ?: "",
                    origin = character.origin ?: "",
                    onItemLongPressed = { onItemLongPressed(character.id ?: -1) },
                    onItemPressed = { onItemPressed(character.id ?: -1) })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(
    characterName: String,
    urlImage: String?,
    status: String,
    species: String,
    origin: String,
    onItemLongPressed: () -> Unit = {},
    onItemPressed: () -> Unit = {}
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onItemPressed() },
                onLongClick = { onItemLongPressed() }
            ),
        elevation = CardDefaults.elevatedCardElevation(dimensions.spaceXxs)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0x51206D0E))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            urlImage?.let {
                GlideImage(
                    model = urlImage,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
            Text(
                text = characterName,
                modifier = Modifier
                    .padding(LocalDimensions.current.spaceSm),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier.padding(LocalDimensions.current.spaceSm),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.green_dot),
                    contentDescription = "",
                    tint = when (status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Black
                    }
                )
                Text(
                    text = "$status - $species",
                    modifier = Modifier.padding(start = dimensions.spaceSm),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
            if (origin != "unknown")
                Text(
                    text = origin,
                    modifier = Modifier.padding(dimensions.spaceSm),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )

        }
    }
}


//@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
//@Composable
//fun CharacterItemPreview() {
//    // Replace with your theme if you have a custom theme
//    MaterialTheme {
//        CharacterItem(
//            characterName = "Sample Character",
//            characterBrand = "Sample Brand",
//            urlImage = "Your local drawable or placeholder image",
//            onItemLongPressed = {}
//        )
//    }
//}

