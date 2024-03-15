package com.example.areader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.areader.screens.ReaderSplashScreen
import com.example.areader.screens.details.BookDetailsScreen
import com.example.areader.screens.home.Home
import com.example.areader.screens.login.ReaderLoginScreen
import com.example.areader.screens.search.SearchScreen
import com.example.areader.screens.stats.ReaderStatsScreen
import com.example.areader.screens.update.BookUpdateScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {

        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {

            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {

            Home(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {

            SearchScreen(navController = navController)
        }

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailsScreen(navController = navController, bookId = it.toString())
            }
        }

        val updateName = ReaderScreens.UpdateScreen.name
        composable("$updateName/{bookItemId}",
            arguments = listOf(navArgument("bookItemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("bookItemId").let {
                BookUpdateScreen(navController = navController, bookItemId = it.toString())
            }

        }

    }
}