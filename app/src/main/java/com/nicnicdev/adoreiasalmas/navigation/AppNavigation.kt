package com.nicnicdev.adoreiasalmas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicnicdev.adoreiasalmas.card.CardResultScreen
import com.nicnicdev.adoreiasalmas.home.HomeScreen
import com.nicnicdev.adoreiasalmas.login.LoginScreen
import com.nicnicdev.adoreiasalmas.login.LoginViewModel
import com.nicnicdev.adoreiasalmas.register.RegisterScreen
import com.nicnicdev.adoreiasalmas.register.RegisterViewModel
import com.nicnicdev.adoreiasalmas.resetpassword.ResetPasswordScreen

const val LOGIN_ROUTE = "login"
const val REGISTER_ROUTE = "register"
const val HOME_ROUTE = "home"
const val CARD_RESULT_ROUTE = "card_result"
const val RESET_PASSWORD_ROUTE = "reset_password"

@Composable
fun AppNavigation() {
    // cria o navControler , que vai controlar qual tela esta visivel
    val navController = rememberNavController()

    NavHost(
        navController = navController, // mapa das rotas
        startDestination = "login" // primeira tela q aparece
    ) {
        // LOGIN
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
                onForgotPasswordClick =  {
                        navController.navigate(RESET_PASSWORD_ROUTE)
            },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        //REGISTER
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
        // RESET PASSWORD
        composable(RESET_PASSWORD_ROUTE) {
            ResetPasswordScreen()
        }
        //HOME
        composable("home") {
            HomeScreen(
                onCardClick = { cardId ->
                    navController.navigate("card_result/$cardId")
                }
            )
        }
        composable(
            route = "card_result/{cardId}",
            arguments = listOf(
                navArgument("cardId") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val cardId = navBackStackEntry.arguments?.getInt("cardId")!!

            CardResultScreen(
                cardId = cardId,
                onBackToHome = {
                    navController.popBackStack("home", false)
                },
                onExitApp = {
                    // se quiser fechar tudo depois a gente ajusta
                    navController.navigate("login") {
                        popUpTo(LOGIN_ROUTE) { inclusive = true }
                    }

                }
            )

    }
    }
}