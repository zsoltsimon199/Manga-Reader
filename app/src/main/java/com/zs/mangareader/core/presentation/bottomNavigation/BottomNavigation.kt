package com.zs.mangareader.core.presentation.bottomNavigation

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zs.mangareader.core.utils.BottomNavItems

@SuppressLint("UnrememberedMutableState")
@Composable
fun BooksBottomNavigation(bottomItems: List<BottomNavItems>, navController: NavHostController) {

    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomItems.map { it.route }

    if (showBottomBar){
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.surfaceDim,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            bottomItems.forEach { item ->

                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title, fontSize = 9.sp)},
                    selectedContentColor = MaterialTheme.colorScheme.secondary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(item.route){
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route){
                                    saveState = false
                                }

                            }
                            launchSingleTop = false
                            restoreState = false
                        }
                    })

            }

        }
    }
}