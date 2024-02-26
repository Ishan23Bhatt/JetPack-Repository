package com.example.jetmovieapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmovieapp.model.Movie
import com.example.jetmovieapp.model.getMovies
import com.example.jetmovieapp.navigation.MovieScreens
import com.example.jetmovieapp.widget.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    Scaffold(
        topBar = { TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = { Text("Movies") }) }
    ) {
            innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainContent(navController)
        }
    }

}

@Composable
fun MainContent(navController: NavController,movieList: List<Movie> = getMovies()){

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(count = movieList.size) { index ->

                val item = movieList[index]
                MovieRow(item){movie ->
                    Log.d("OnItemClick","Movie name $movie")
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }

}