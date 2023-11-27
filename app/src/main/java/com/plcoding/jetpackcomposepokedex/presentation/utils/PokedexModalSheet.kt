package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    val selectedItem = remember { mutableStateOf(items[0]) }
    val names = listOf("Generations","All Pokemon","Favorites")
    val selectedName = remember { mutableStateOf(names[0]) }
    val navList = listOf("generation_list_screen", "pokemon_list_screen", "")
    val selectedNavList = remember { mutableStateOf(navList[0]) }

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        NavigationDrawerItem(
            label = { Text(text = "Menu") },
            selected = false,
            onClick = { /*TODO*/ }
        )
        items.indices.forEach { index ->
            val item = items[index]
            val name = names[index]
            val nav = navList[index]
            NavigationDrawerItem(
                icon = { Icon(item, contentDescription = null) },
                label = {
                    Text(text = name)
                },
                selected = item == selectedItem.value,
                onClick = {
                    selectedItem.value = item
                    selectedName.value = name
                    selectedNavList.value = nav
                    navController.navigate(selectedNavList.value)
                    scope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
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