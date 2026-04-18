package ru.saytikus.androidsimpleclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.saytikus.androidsimpleclient.presentation.NavigationGraph
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSimpleClientTheme(
                content = ::NavigationGraph
            )
        }
    }
}