package com.example.mpazpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mpazpro.ui.pantallas.PantallaDocente
import com.example.mpazpro.ui.pantallas.PantallaEstudiante
import com.example.mpazpro.ui.pantallas.PantallaLogin
import com.example.mpazpro.ui.pantallas.PantallaRegistro
import com.example.mpazpro.ui.pantallas.PantallaUtp
import com.example.mpazpro.ui.pantallas.PerfilUsuario
import com.example.mpazpro.ui.theme.MpazProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MpazProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {

                        composable("login") {
                            PantallaLogin(
                                onIngresar = { perfil ->
                                    when (perfil) {
                                        PerfilUsuario.DOCENTE -> navController.navigate("docente")
                                        PerfilUsuario.UTP -> navController.navigate("utp")
                                        PerfilUsuario.ESTUDIANTE -> navController.navigate("estudiante")
                                    }
                                },
                                onIrRegistro = {
                                    navController.navigate("registro")
                                }
                            )
                        }

                        composable("registro") {
                            PantallaRegistro(
                                onVolver = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("docente") {
                            PantallaDocente(
                                onVolver = { navController.navigate("login") { popUpTo(0) } }
                            )
                        }

                        composable("utp") {
                            PantallaUtp(
                                onVolver = { navController.navigate("login") { popUpTo(0) } }
                            )
                        }

                        composable("estudiante") {
                            PantallaEstudiante(
                                onVolver = { navController.navigate("login") { popUpTo(0) } }
                            )
                        }
                    }
                }
            }
        }
    }
}