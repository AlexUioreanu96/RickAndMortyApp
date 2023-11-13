package com.example.fashionday.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.fashionday.ui.theme.LocalDimensions
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val colors = MaterialTheme.colorScheme
    val dimensions = LocalDimensions.current
    val typography = MaterialTheme.typography

    val uiState = viewModel.uiState.collectAsState().value


    val pullRefreshState = rememberSwipeRefreshState(isRefreshing = true)

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(dimensions.spaceMd),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = colors.primary,
                        shape = RoundedCornerShape(
                            dimensions.spaceXs
                        )
                    )
                    .padding(dimensions.spaceMd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Fashion Days",
                    style = typography.headlineLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = colors.primary
                    )
                )
            }
        }) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
//            ProductGrid(
//                products = uiState.characters,
//                onItemLongPressed = { productId ->
//                    viewModel.deleteItemOnLongPressed(productId)
//                },
//                pullRefreshState = pullRefreshState,
//                onRefresh = viewModel::fetchCharacters
//            )
        }
    }
}

//@Composable
//fun ProductGrid(
//    products: List<Product>,
//    onItemLongPressed: (Int) -> Unit,
//    pullRefreshState: SwipeRefreshState,
//    onRefresh: () -> Unit
//) {
//    val scrollState = rememberLazyGridState()
//    val dimensions = LocalDimensions.current
//
//    SwipeRefresh(state = pullRefreshState, onRefresh = onRefresh) {
//        LazyVerticalGrid(
//            state = scrollState,
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .padding(top = dimensions.spaceXs)
//                .fillMaxSize()
//        ) {
//            items(products) { product ->
//                ProductItem(
//                    productName = product.productName,
//                    productBrand = product.productBrand,
//                    urlImage = product.productImages.thumb?.get(0),
//                    onItemLongPressed = { onItemLongPressed(product.productId) }
//                )
//            }
//        }
//    }
//}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    productName: String,
    productBrand: String,
    urlImage: String?,
    onItemLongPressed: () -> Unit = {},
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.spaceXs)
            .height(350.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = { onItemLongPressed() }
            ),
        elevation = CardDefaults.elevatedCardElevation(dimensions.spaceXxs)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            urlImage?.let {
                GlideImage(
                    model = urlImage,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }
            Text(
                text = productBrand,
                modifier = Modifier
                    .padding(LocalDimensions.current.spaceSm),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center
            )
            Text(
                text = productName,
                modifier = Modifier
                    .padding(LocalDimensions.current.spaceSm),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

