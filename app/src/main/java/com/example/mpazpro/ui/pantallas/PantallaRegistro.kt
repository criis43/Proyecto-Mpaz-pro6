package com.example.mpazpro.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaRegistro(onVolver: () -> Unit) {
    // 1. Estados para guardar lo que escribe el usuario
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // 2. Estados para manejar los errores (True = hay error)
    var errorNombre by remember { mutableStateOf(false) }
    var errorCorreo by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón para volver atrás
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onVolver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Textos del encabezado
        Text(text = "Crear Cuenta", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Regístrate para acceder a MPAZ PRO 6.0", fontSize = 15.sp, color = Color(0xFF64748B))

        Spacer(modifier = Modifier.height(32.dp))

        // --- CAMPO: NOMBRE ---
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it; errorNombre = false },
            label = { Text("Nombre completo") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            isError = errorNombre,
            supportingText = { if (errorNombre) Text("El nombre es obligatorio") },
            trailingIcon = { if (errorNombre) Icon(Icons.Default.Error, contentDescription = "Error", tint = MaterialTheme.colorScheme.error) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPO: CORREO ---
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it; errorCorreo = false },
            label = { Text("Correo electrónico") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            isError = errorCorreo,
            supportingText = { if (errorCorreo) Text("Ingresa un correo válido (debe contener @)") },
            trailingIcon = { if (errorCorreo) Icon(Icons.Default.Error, contentDescription = "Error", tint = MaterialTheme.colorScheme.error) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- CAMPO: CONTRASEÑA ---
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; errorPassword = false },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorPassword,
            supportingText = { if (errorPassword) Text("La contraseña debe tener al menos 6 caracteres") },
            trailingIcon = { if (errorPassword) Icon(Icons.Default.Error, contentDescription = "Error", tint = MaterialTheme.colorScheme.error) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- BOTÓN DE REGISTRO CON LÓGICA DE VALIDACIÓN ---
        Button(
            onClick = {
                // Evaluamos las reglas de negocio
                val nombreValido = nombre.isNotBlank()
                val correoValido = correo.contains("@") && correo.contains(".")
                val passwordValida = password.length >= 6

                // Actualizamos los estados visuales de error
                errorNombre = !nombreValido
                errorCorreo = !correoValido
                errorPassword = !passwordValida

                // Si todo está correcto, avanzamos (Por ahora, solo simulamos éxito volviendo al Login)
                if (nombreValido && correoValido && passwordValida) {
                    onVolver() // Regresa al login
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C63E6))
        ) {
            Text(text = "Registrarse", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}