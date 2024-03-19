package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PokedexNavigationBar(
    navController: NavController,
) {
    val items = listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Notifications)
    val navList: List<String?> = listOf("home_screen", "", "")

    NavigationBar(
        modifier = Modifier
            .height(55.dp),
        containerColor = NavigationBarDefaults.containerColor,
        tonalElevation = 3.dp
    ) {
        items.indices.forEach { index ->
            val item = items[index]
            val nav = navList[index]

            NavigationBarItem(
                selected = navController.currentDestination?.route == nav,
                onClick = {
                    if (!nav.isNullOrEmpty()) {
                        navController.navigate(nav)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewPokemonBottomAppBar(){
    PokedexNavigationBar(
        navController = rememberNavController(),
    )
}