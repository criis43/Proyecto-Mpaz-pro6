package com.example.mpazpro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mpazpro.ui.pantallas.PantallaDocente
import com.example.mpazpro.ui.pantallas.PantallaLogin
import com.example.mpazpro.ui.pantallas.PantallaUtp
import com.example.mpazpro.ui.pantallas.PerfilUsuario

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val contexto = LocalContext.current

                    NavHost(navController = navController, startDestination = "login") {

                        // Ruta de Login
                        composable("login") {
                            PantallaLogin(
                                onIngresar = { perfil ->
                                    when (perfil) {
                                        PerfilUsuario.DOCENTE -> navController.navigate("docente")
                                        PerfilUsuario.UTP -> navController.navigate("utp")
                                        PerfilUsuario.ESTUDIANTE -> {
                                            Toast.makeText(contexto, "Vista de Estudiante delegada a compañero", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            )
                        }

                        // Ruta del Docente
                        composable("docente") {
                            PantallaDocente(
                                onVolver = {
                                    navController.navigate("login") {
                                        popUpTo(0) // Borra el historial para no poder regresar con el botón físico del móvil
                                    }
                                }
                            )
                        }
                        // Ruta de la UTP
                        composable("utp") {
                            PantallaUtp(
                                onVolver = {
                                    navController.navigate("login") {
                                        popUpTo(0)
                                    }
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}