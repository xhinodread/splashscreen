package com.example.splashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.ui.MainScreen
import com.example.splashscreen.ui.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val AppSplashScreenRoute = AppScreens.SplashScreen.route
    val AppMainScreenRoute = AppScreens.MainScreen.route
    NavHost(
        navController = navController,
        startDestination = AppSplashScreenRoute
    ){
        composable(AppSplashScreenRoute){
            SplashScreen(navController)
        }
        composable(AppMainScreenRoute){
            MainScreen()
        }
    }
}