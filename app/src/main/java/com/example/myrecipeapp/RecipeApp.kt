package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel:MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesSate

    NavHost(navController= navController, startDestination = Screen.RecipeScreen.route){
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewState, navigateToDetail = {
                //This part is responsible for passing data from the current screen to detail screen.
                //It utilizes the savedStateHandle, which is a component of the compose navigation framework.
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route =  Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                    get<Category>("cat") ?: Category("","","","")

            CategoryDetailScreen(category = category)
        }
    }

}