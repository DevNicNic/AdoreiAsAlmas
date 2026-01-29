package com.nicnicdev.adoreiasalmas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicnicdev.adoreiasalmas.home.HomeScreen
import com.nicnicdev.adoreiasalmas.login.LoginScreen
import com.nicnicdev.adoreiasalmas.login.LoginViewModel
import com.nicnicdev.adoreiasalmas.register.RegisterScreen
import com.nicnicdev.adoreiasalmas.register.RegisterViewModel


@Composable
fun AppNavigation() {
    // cria o navControler , que vai controlar qual tela esta visivel
    val navController = rememberNavController()

    NavHost(
        navController = navController, // mapa das rotas
        startDestination = "login" // primeira tela q aparece
    ) {
        // Rota da tela
        composable("login") {
            // Cria o ViewModel da tela de login
            val loginViewModel: LoginViewModel = viewModel()

            // chama a função com parametros
            LoginScreen(
                loginViewModel=loginViewModel,
                onCreateAccountClick = {
                    // navega para rota
                    navController.navigate("register") // vai para tela de cadastro
                },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("register") {
            //cria o viewModel da tela de cadastro
            val registerViewModel: RegisterViewModel = viewModel()

            RegisterScreen(
                viewModel= registerViewModel,
                onRegisterSuccess = {
                    navController.navigate("login"){
                        popUpTo("register") {inclusive = true}
                    }
                }
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}