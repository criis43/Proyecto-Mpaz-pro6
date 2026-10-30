package com.example.mpazpro.ui.pantallas

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mpazpro.ui.viewmodel.UtpViewModel
import com.example.mpazpro.util.NotificacionesHelper

enum class EstadoMaterial { PENDIENTE, APROBADO, RECHAZADO }

data class MaterialAcademico(
    val id: Int,
    val asignatura: String,
    val titulo: String,
    val detalles: String,
    val icono: ImageVector,
    val colorIcono: Color,
    val fondoIcono: Color,
    var estado: EstadoMaterial = EstadoMaterial.PENDIENTE
)

@Composable
fun PantallaUtp(
    onVolver: () -> Unit = {},
    viewModel: UtpViewModel = viewModel() // ARQUITECTURA MVVM
) {
    val contexto = LocalContext.current

    val launcherPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { }

    LaunchedEffect(Unit) {
        NotificacionesHelper.crearCanalNotificacion(contexto)
        viewModel.cargarMateriales(contexto)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                launcherPermiso.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    val pendientes = viewModel.listaMateriales.count { it.estado == EstadoMaterial.PENDIENTE }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        BarraLateralUtp(onVolver = onVolver)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "GESTIÓN DE MATERIAL", color = Color(0xFF4C63E6), fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Autorización\npedagógica", color = Color(0xFF0F172A), fontSize = 32.sp, fontWeight = FontWeight.Bold, lineHeight = 40.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Revisa el material antes de que sea publicado para estudiantes.", color = Color(0xFF64748B), fontSize = 15.sp, lineHeight = 22.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFFFFBEB),
                border = BorderStroke(1.dp, Color(0xFFFDE68A))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFFEF3C7), modifier = Modifier.size(40.dp)) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Schedule, contentDescription = null, tint = Color(0xFFD97706), modifier = Modifier.size(20.dp))
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "$pendientes pendientes", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF0F172A))
                            Text(text = "Requieren tu revisión", color = Color(0xFF64748B), fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            viewModel.listaMateriales.forEach { material ->
                TarjetaRevisionMaterial(
                    material = material,
                    onAprobar = { viewModel.cambiarEstado(contexto, material.id, EstadoMaterial.APROBADO) },
                    onRechazar = { viewModel.cambiarEstado(contexto, material.id, EstadoMaterial.RECHAZADO) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TarjetaRevisionMaterial(
    material: MaterialAcademico,
    onAprobar: () -> Unit,
    onRechazar: () -> Unit
) {
    // ANIMACIÓN 1: Cambio de color de fondo suavizado
    val colorBordeAnimado by animateColorAsState(
        targetValue = when (material.estado) {
            EstadoMaterial.APROBADO -> Color(0xFFBBF7D0)
            EstadoMaterial.RECHAZADO -> Color(0xFFFECACA)
            EstadoMaterial.PENDIENTE -> Color(0xFFE2E8F0)
        },
        animationSpec = tween(durationMillis = 400),
        label = "colorBorde"
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, colorBordeAnimado),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Surface(shape = RoundedCornerShape(12.dp), color = material.fondoIcono, modifier = Modifier.size(56.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(material.icono, contentDescription = null, tint = material.colorIcono, modifier = Modifier.size(28.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = material.asignatura, color = Color(0xFF4C63E6), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = material.titulo, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = material.detalles, color = Color(0xFF64748B), fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ANIMACIÓN 2: Transición fluida con AnimatedVisibility entre botones y la confirmación
            AnimatedVisibility(
                visible = material.estado == EstadoMaterial.PENDIENTE,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onRechazar,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFFCA5A5)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFDC2626))
                    ) {
                        Text("Rechazar", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }

                    Button(
                        onClick = onAprobar,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C63E6))
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Aprobar y autorizar", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }

            AnimatedVisibility(
                visible = material.estado != EstadoMaterial.PENDIENTE,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = if (material.estado == EstadoMaterial.APROBADO) Color(0xFFDCFCE7) else Color(0xFFFEE2E2)
                ) {
                    Box(modifier = Modifier.padding(14.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = if (material.estado == EstadoMaterial.APROBADO) "✅ Material Aprobado" else "❌ Material Rechazado",
                            fontWeight = FontWeight.Bold,
                            color = if (material.estado == EstadoMaterial.APROBADO) Color(0xFF166534) else Color(0xFF991B1B)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BarraLateralUtp(onVolver: () -> Unit) {
    var fotoPerfilUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> fotoPerfilUri = uri }

    Column(
        modifier = Modifier.width(80.dp).fillMaxHeight().background(Color(0xFF1E293B)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // LOGO DE LA APP DESTACADO CON COLOR INVERTIDO
        Surface(
            color = Color(0xFF4C63E6),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(44.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                com.example.mpazpro.ui.componentes.LogoMpaz(tint = Color.White, modifier = Modifier.size(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Surface(color = Color(0xFF334155), shape = RoundedCornerShape(12.dp), modifier = Modifier.size(44.dp)) {
            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Article, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(Icons.Default.TrendingUp, null, tint = Color(0xFF94A3B8), modifier = Modifier.size(24.dp))

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onVolver) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color(0xFF94A3B8), modifier = Modifier.size(28.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            color = Color(0xFF475569),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).clickable { galleryLauncher.launch("image/*") }
        ) {
            if (fotoPerfilUri != null) {
                AsyncImage(model = fotoPerfilUri, contentDescription = "Foto", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            } else {
                Box(contentAlignment = Alignment.Center) { Text("CM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}