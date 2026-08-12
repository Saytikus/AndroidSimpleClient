package ru.saytikus.androidsimpleclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.android.ext.android.inject
import ru.saytikus.androidsimpleclient.navigation.AppNavigationCoordinator
import ru.saytikus.androidsimpleclient.navigation.AppNavigationRouter
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val coordinator: AppNavigationCoordinator by inject()

        setContent {
            AndroidSimpleClientTheme(
                content = { AppNavigationRouter(coordinator) }
            )
        }
    }
}