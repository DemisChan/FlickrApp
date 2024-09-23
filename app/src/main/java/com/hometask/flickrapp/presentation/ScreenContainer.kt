package com.hometask.flickrapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hometask.flickrapp.viewmodel.FlickrViewModel


@Composable
fun ScreenContainer(
    viewModel: FlickrViewModel,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    MainNavHost(
        navController = navController,
        modifier = modifier,
        viewModel = viewModel
    )

}

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FlickrViewModel,
) {
    NavHost(navController = navController, startDestination = "photoList") {
        composable("photoList") {
            PhotoListScreenContainer(
                modifier = modifier,
                viewModel = viewModel,
                onNavigate = { it ->
                    navController.navigate(it)
                }
            )
        }
        composable("photoDetail/{photoId}") { backStackEntry ->
                PhotoDetailScreenContainer(
                    viewModel = viewModel,
                    modifier = modifier
                )
        }
        composable("userPhotos/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            userId?.let {
                UserPhotoListScreenContainer(
                    userId = it,
                    viewModel = viewModel,
                    modifier = modifier,
                    onNavigate = { it ->
                        navController.navigate(it)
                    }
                )
            }
        }
    }
}
