package com.example.fashionday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fashionday.ui.screen.main.MainScreen
import com.example.fashionday.ui.theme.FashionDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FashionDayTheme {
                MainScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
