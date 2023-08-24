package com.zs.mangareader.featureBrowse.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zs.mangareader.core.presentation.reader.MainActivity
import com.zs.mangareader.core.utils.BottomNavItems

@Composable
fun BrowseComposable(
    navController: NavController, viewModel: BrowseViewModel = hiltViewModel()
) {
    val mangas = viewModel.mangas.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val error = viewModel.error.collectAsState()
    var hasBeenTouched by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        MainActivity.topBarTitle.value = BottomNavItems.BROWSE.title

        //viewModel.getMangas()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 65.dp, start = 10.dp, end = 10.dp, bottom = 55.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(5.dp, 15.dp)
        ) {
            items(10) {
                Card(Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                ) {
                    Text(text = "asd")
                }
            }
        }
    }
    


    //Log.d("debug", mangas.value.toString())

}