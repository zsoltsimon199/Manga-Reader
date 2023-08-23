package com.zs.mangareader.core.presentation.reader

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zs.mangareader.R
import com.zs.mangareader.core.domain.ConnectivityObserver
import com.zs.mangareader.core.domain.NetworkConnectivityObserver
import com.zs.mangareader.core.presentation.bottomNavigation.BooksBottomNavigation
import com.zs.mangareader.core.presentation.reader.ui.ThemeChooser
import com.zs.mangareader.core.presentation.reader.ui.theme.MangaReaderTheme
import com.zs.mangareader.core.utils.BottomNavItems
import com.zs.mangareader.core.utils.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        var topBarTitle = MutableStateFlow("Default")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MangaReaderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    val navController = rememberNavController()
                    BottomNavigationView(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomNavigationView(navController: NavHostController, viewModel: MainViewModel) {

    val items = listOf(
        BottomNavItems.LIBRARY, BottomNavItems.UPDATES, BottomNavItems.HISTORY, BottomNavItems.BROWSE, BottomNavItems.MORE
    )

    val isDetailsScreen =
        navController.currentBackStackEntryAsState().value?.destination?.route !in items.map { it.route }
    val title = MainActivity.topBarTitle.collectAsState()
    val isDarkTheme = ThemeChooser.isDarkTheme.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title.value,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (!isDetailsScreen) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {  viewModel.saveTheme(!isDarkTheme.value) }) {
                            if (isDarkTheme.value) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_light_mode),
                                    contentDescription = "Toggle Light Mode"
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_dark_mode),
                                    contentDescription = "Toggle Dark Mode"
                                )
                            }
                        }
                    }
                }
            }

        }, navigationIcon = {
            if (isDetailsScreen) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            }
        })
    }, bottomBar = {
        BooksBottomNavigation(bottomItems = items, navController = navController)
    }, content = { padding ->
        ContentView(
            modifier = Modifier.padding(padding), navController = navController
        )
    })

}


@Composable
fun ContentView(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    val connectivityObserver = NetworkConnectivityObserver(context)
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )
    if (status == ConnectivityObserver.Status.Lost || status == ConnectivityObserver.Status.Losing || status == ConnectivityObserver.Status.Unavailable) {

        NoNetworkConnection()
    } else {
        NavHost(navController = navController, startDestination = Routes.LIBRARY) {
            composable(route = Routes.LIBRARY) {
                LaunchedEffect(Unit) {
                    MainActivity.topBarTitle.value = BottomNavItems.LIBRARY.title
                }
                Text(text = BottomNavItems.LIBRARY.title)
            }

            composable(route = Routes.UPDATES) {
                LaunchedEffect(Unit) {
                    MainActivity.topBarTitle.value = BottomNavItems.UPDATES.title
                }
                Text(text = BottomNavItems.UPDATES.title)
            }

            composable(route = Routes.HISTORY) {
                LaunchedEffect(Unit) {
                    MainActivity.topBarTitle.value = BottomNavItems.HISTORY.title
                }
                Text(text = BottomNavItems.HISTORY.title)
            }

            composable(route = Routes.BROWSE) {
                LaunchedEffect(Unit) {
                    MainActivity.topBarTitle.value = BottomNavItems.BROWSE.title
                }
                Text(text = BottomNavItems.BROWSE.title)
            }

            composable(route = Routes.MORE) {
                LaunchedEffect(Unit) {
                    MainActivity.topBarTitle.value = BottomNavItems.MORE.title
                }
                Text(text = BottomNavItems.MORE.title)
            }
        }
    }
}

@Composable
fun NoNetworkConnection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "There is some problem with your network connection",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}