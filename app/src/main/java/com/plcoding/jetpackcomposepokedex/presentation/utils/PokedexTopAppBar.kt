package com.plcoding.jetpackcomposepokedex.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexTopAppBar(
    drawerState: DrawerState,
    navController: NavController,
) {

    val scope = rememberCoroutineScope()
    val currentDestination = navController.currentDestination?.route

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier.height(55.dp),
        title = {
            when(currentDestination) {
                "home_screen" -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                }
                "pokemon_list_screen" -> {
                    SearchBar()
                }
                else -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                    ,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                    ,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
    )
}

@Preview
@Composable
fun PreviewPokemonTopAppBar(){
    PokedexTopAppBar(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        navController = rememberNavController(),
    )
}