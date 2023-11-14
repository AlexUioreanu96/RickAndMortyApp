package com.example.fashionday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fashionday.ui.screen.details.DetailsScreen
import com.example.fashionday.ui.screen.favorites.FavoritesScreen
import com.example.fashionday.ui.screen.main.MainScreen
import com.example.fashionday.ui.screen.splash.SplashScreen
import com.example.fashionday.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Scaffold(
                    bottomBar = { }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        NavigationGraph()
                    }
                }
            }
        }
    }

}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen() }
        composable("favorite") { FavoritesScreen() }
        composable("details/{id}") { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("id") ?: "")
        }
    }
}


