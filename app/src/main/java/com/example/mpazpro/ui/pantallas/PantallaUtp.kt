package com.example.mpazpro.ui.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Article
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- MODELO DE DATOS Y ESTADOS ---
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
fun PantallaUtp(onVolver: () -> Unit = {}) {
    // Lista interactiva de materiales simulando la base de datos
    val listaMateriales = remember {
        mutableStateListOf(
            MaterialAcademico(1, "LENGUAJE Y COMUNICACIÓN", "Unidad 1 · El poder de las historias", "Contenido y 5 actividades Por María González Hoy, 09:42", Icons.Default.Book, Color(0xFF4C63E6), Color(0xFFEEF2FF)),
            MaterialAcademico(2, "MATEMÁTICA", "Unidad 1 · Números y operaciones", "Contenido y 6 actividades Por Felipe Araya Ayer, 16:18", Icons.Default.Calculate, Color(0xFF2563EB), Color(0xFFDBEAFE)),
            MaterialAcademico(3, "CIENCIAS NATURALES", "Unidad 1 · La energía", "Contenido y 4 actividades Por Paula Méndez 12 may, 11:05", Icons.Default.Science, Color(0xFF16A34A), Color(0xFFDCFCE7)),
            MaterialAcademico(4, "HISTORIA, GEOGRAFÍA Y CS. SOCIALES", "Unidad 1 · Chile en el siglo XX", "Contenido y 5 actividades Por Jorge Fuentes 10 may, 14:30", Icons.Default.History, Color(0xFFD97706), Color(0xFFFEF3C7))
        )
    }

    // Contador dinámico de pendientes
    val pendientes = listaMateriales.count { it.estado == EstadoMaterial.PENDIENTE }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        // Barra Lateral
        BarraLateralUtp(onVolver = onVolver)

        // Contenido Principal
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Encabezados
            Text(text = "GESTIÓN DE MATERIAL", color = Color(0xFF4C63E6), fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Autorización\npedagógica", color = Color(0xFF0F172A), fontSize = 32.sp, fontWeight = FontWeight.Bold, lineHeight = 40.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Revisa el material antes de que sea publicado para estudiantes.", color = Color(0xFF64748B), fontSize = 15.sp, lineHeight = 22.sp)

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta de Pendientes Dinámica
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(text = "Periodo activo ", color = Color(0xFF64748B), fontSize = 14.sp)
                        Text(text = "1.er semestre 2025", fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Subtítulo de Sección
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Material cargado", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Text(text = "Máximo una unidad activa por asignatura", fontSize = 14.sp, color = Color(0xFF64748B))
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    color = Color.White
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
                        Text(text = "Todos los\nestados", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF0F172A), modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Tarjetas
            listaMateriales.forEachIndexed { index, material ->
                TarjetaRevisionMaterial(
                    material = material,
                    onAprobar = { listaMateriales[index] = material.copy(estado = EstadoMaterial.APROBADO) },
                    onRechazar = { listaMateriales[index] = material.copy(estado = EstadoMaterial.RECHAZADO) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun TarjetaRevisionMaterial(
    material: MaterialAcademico,
    onAprobar: () -> Unit,
    onRechazar: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                // Icono Asignatura
                Surface(shape = RoundedCornerShape(12.dp), color = material.fondoIcono, modifier = Modifier.size(56.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(material.icono, contentDescription = null, tint = material.colorIcono, modifier = Modifier.size(28.dp))
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Textos
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = material.asignatura, color = Color(0xFF4C63E6), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = material.titulo, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), lineHeight = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Article, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = material.detalles, color = Color(0xFF64748B), fontSize = 13.sp, lineHeight = 18.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botones o Estado Funcional
            if (material.estado == EstadoMaterial.PENDIENTE) {
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
            } else {
                // Muestra un indicador visual si ya se tomó una decisión
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
    Column(
        modifier = Modifier.width(80.dp).fillMaxHeight().background(Color(0xFF1E293B)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Surface(color = Color(0xFF334155), shape = RoundedCornerShape(12.dp), modifier = Modifier.size(44.dp)) {
            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Book, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Surface(color = Color(0xFF334155), shape = RoundedCornerShape(12.dp), modifier = Modifier.size(44.dp)) {
            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Article, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(Icons.Default.TrendingUp, null, tint = Color(0xFF94A3B8), modifier = Modifier.size(24.dp))

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onVolver) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver al inicio", tint = Color(0xFF94A3B8), modifier = Modifier.size(28.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(color = Color(0xFF475569), shape = RoundedCornerShape(12.dp), modifier = Modifier.size(44.dp)) {
            Box(contentAlignment = Alignment.Center) { Text("CM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}