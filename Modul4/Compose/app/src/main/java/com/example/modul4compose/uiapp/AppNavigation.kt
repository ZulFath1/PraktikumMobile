package com.example.modul4compose.uiapp

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import timber.log.Timber

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Inisialisasi ViewModel dengan Factory
    val viewModel: FriendViewModel = viewModel(
        factory = FriendViewModelFactory("Fathi")
    )

    NavHost(
        navController = navController,
        startDestination = "list_screen",
        modifier = modifier
    ) {
        // HALAMAN 1: LIST SCREEN
        composable("list_screen") {
            val friends by viewModel.friendsState.collectAsState()
            val context = LocalContext.current

            FriendListScreen(
                friends = friends,
                onNavigateToDetail = { friend ->
                    // Log saat tombol Detail ditekan
                    // Log data dari list yang dipilih
                    viewModel.onFriendClicked(friend)
                    navController.navigate("detail_screen/${friend.id}")
                },
                onInstagramClick = { url ->
                    // Log saat tombol Explicit Intent (Instagram) ditekan
                    Timber.d("Tombol Explicit Intent (Instagram) ditekan. Membuka URL: $url")

                    // Logika Explicit Intent
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }

        // HALAMAN 2: DETAIL SCREEN (Dikembalikan lagi ke sini)
        composable(
            route = "detail_screen/{friendId}",
            arguments = listOf(navArgument("friendId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("friendId") ?: 0
            DetailScreen(friendId = id)
        }
    }
}