package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PokedexModalSheet(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController
) {
    val items = listOf(Icons.Default.List, Icons.Default.ListAlt, Icons.Default.Favorite)
    val names = listOf("Generations","All Pokemon","Favorites")
    val navList = listOf("generation_list_screen", "pokemon_list_screen", "")

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        NavigationDrawerItem(
            label = { Text(
                text = "Menu",
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    lineHeight = MaterialTheme.typography.titleMedium.lineHeight,

                )
            ) },
            selected = false,
            onClick = { /*TODO*/ }
        )
        Divider(color = MaterialTheme.colorScheme.outline)
        items.indices.forEach { index ->
            val item = items[index]
            val name = names[index]
            val nav = navList[index]
            NavigationDrawerItem(
                icon = { Icon(
                    item,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                ) },
                label = {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            lineHeight = MaterialTheme.typography.titleLarge .lineHeight,

                        )
                    )
                },
                selected = navController.currentDestination?.route == nav,
                onClick = {
                    navController.navigate(nav)
                    scope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
        Divider(color = MaterialTheme.colorScheme.outline)
    }
}


@Preview
@Composable
fun PreviewPokedexModalSheet() {
    PokedexModalSheet(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        scope = rememberCoroutineScope(),
        navController = rememberNavController()
    )
}