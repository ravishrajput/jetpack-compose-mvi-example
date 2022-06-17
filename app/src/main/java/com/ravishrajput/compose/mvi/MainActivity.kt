package com.ravishrajput.compose.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ravishrajput.compose.mvi.navigation.AppNavigation
import com.ravishrajput.compose.mvi.ui.theme.JetpackcomposemviexampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JetpackcomposemviexampleTheme {
        AppNavigation()
      }
    }
  }
}
