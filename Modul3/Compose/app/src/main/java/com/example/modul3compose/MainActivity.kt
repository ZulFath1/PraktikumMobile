package com.example.modul3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul3compose.ui.DetailScreen
import com.example.modul3compose.ui.FriendListScreen
import com.example.modul3compose.ui.theme.Modul3ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "list_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("list_screen") {
                            FriendListScreen(
                                onNavigateToDetail = { friendId ->
                                    navController.navigate("detail_screen/$friendId")
                                }
                            )
                        }

                        composable(
                            route = "detail_screen/{friendId}",
                            arguments = listOf(navArgument("friendId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("friendId") ?: 0

                            DetailScreen(friendId = id)
                        }
                    }

                }
            }
        }
    }
}