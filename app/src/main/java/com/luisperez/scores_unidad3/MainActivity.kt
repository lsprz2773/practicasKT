package com.luisperez.scores_unidad3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.luisperez.scores_unidad3.domain.model.Destination
import com.luisperez.scores_unidad3.ui.navigation.AppNavHost
import com.luisperez.scores_unidad3.ui.theme.Scores_unidad3Theme
import com.luisperez.scores_unidad3.ui.viewModels.DashboardViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dashboardViewModel: DashboardViewModel by viewModels()
        setContent {
            Scores_unidad3Theme {
                val navController = rememberNavController()
                val startDestination = Destination.STUDENT
                var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal    ) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxWidth(),
                    bottomBar = {
                        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                            Destination.entries.forEachIndexed { index, destination ->
                                NavigationBarItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = destination.route)
                                        selectedDestination = index
                                    },
                                    icon = {
                                        Icon(destination.icon,
                                            contentDescription = destination.contentDescription)
                                    },
                                    label = {Text(destination.label)}
                                )
                            }
                        }
                    }
                ) { contentPadding ->
                    AppNavHost(navController,startDestination, modifier = Modifier.padding(contentPadding), dashboardViewModel)
                }

            }
        }
    }
}
