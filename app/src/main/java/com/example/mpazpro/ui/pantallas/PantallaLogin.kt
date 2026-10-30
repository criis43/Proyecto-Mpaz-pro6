package com.example.mpazpro.ui.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mpazpro.ui.componentes.LogoMpaz

enum class PerfilUsuario { DOCENTE, UTP, ESTUDIANTE }

@Composable
fun PantallaLogin(
    onIngresar: (PerfilUsuario) -> Unit,
    onIrRegistro: () -> Unit
) {
    var perfilSeleccionado by remember { mutableStateOf(PerfilUsuario.ESTUDIANTE) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFAFAFA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))

                // Logo Oficial de la App
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF4C63E6),
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            LogoMpaz(tint = Color.White, modifier = Modifier.size(26.dp))
                        }
                    }
                    Text(
                        text = "MPAZ PRO 6.0",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "BIENVENIDO/A",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4C63E6),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Ingresa a tu espacio",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Selecciona tu perfil para continuar.",
                    fontSize = 15.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(28.dp))

                TarjetaPerfil(
                    titulo = "Docente",
                    icono = Icons.Default.Person,
                    seleccionado = perfilSeleccionado == PerfilUsuario.DOCENTE,
                    onSelect = { perfilSeleccionado = PerfilUsuario.DOCENTE }
                )

                Spacer(modifier = Modifier.height(12.dp))

                TarjetaPerfil(
                    titulo = "UTP",
                    icono = Icons.Default.Shield,
                    seleccionado = perfilSeleccionado == PerfilUsuario.UTP,
                    onSelect = { perfilSeleccionado = PerfilUsuario.UTP }
                )

                Spacer(modifier = Modifier.height(12.dp))

                TarjetaPerfil(
                    titulo = "Estudiante",
                    icono = Icons.Default.School,
                    seleccionado = perfilSeleccionado == PerfilUsuario.ESTUDIANTE,
                    onSelect = { perfilSeleccionado = PerfilUsuario.ESTUDIANTE }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Button(
                    onClick = { onIngresar(perfilSeleccionado) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C63E6))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Ingresar", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = onIrRegistro) {
                    Text("¿No tienes cuenta? Regístrate aquí", color = Color(0xFF4C63E6), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun TarjetaPerfil(
    titulo: String,
    icono: ImageVector,
    seleccionado: Boolean,
    onSelect: () -> Unit
) {
    val borderColor = if (seleccionado) Color(0xFF4C63E6) else Color(0xFFE2E8F0)
    val backgroundColor = if (seleccionado) Color(0xFFF8FAFC) else Color.White

    Surface(
        modifier = Modifier.fillMaxWidth().clickable { onSelect() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(if (seleccionado) 2.dp else 1.dp, borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (seleccionado) Color(0xFFEEF2FF) else Color(0xFFF1F5F9),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icono, contentDescription = null, tint = Color(0xFF4C63E6), modifier = Modifier.size(22.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
            }
            RadioButton(
                selected = seleccionado,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF4C63E6), unselectedColor = Color(0xFFCBD5E1))
            )
        }
    }
}