package com.example.mpazpro.ui.pantallas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PantallaDocente(onVolver: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        BarraLateral(onVolver = onVolver)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "6.º BÁSICO", color = Color(0xFF4C63E6), fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Configuración del curso", color = Color(0xFF0F172A), fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Organiza los objetivos de aprendizaje y define la evaluación final.", color = Color(0xFF64748B), fontSize = 15.sp)

            Spacer(modifier = Modifier.height(24.dp))
            TarjetaAsignatura()

            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Objetivos de aprendizaje", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Text(text = "3 objetivos incluidos en esta unidad", fontSize = 13.sp, color = Color(0xFF64748B))
                }
                Surface(color = Color(0xFFDCFCE7), shape = RoundedCornerShape(12.dp), modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = "Contenido\ncompleto", color = Color(0xFF166534), fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TarjetaObjetivo("OA 03", Color(0xFF4C63E6), Color(0xFFEEF2FF), "Comprender textos aplicando estrategias de lectura", "Comprensión lectora")
            Spacer(modifier = Modifier.height(12.dp))
            TarjetaObjetivo("OA 09", Color(0xFF2563EB), Color(0xFFDBEAFE), "Desarrollar el gusto por la lectura, leyendo habitualmente", "Lectura autónoma")
            Spacer(modifier = Modifier.height(12.dp))
            TarjetaObjetivo("OA 18", Color(0xFFEA580C), Color(0xFFFFEDD5), "Escribir, revisar y editar sus textos para satisfacer un propósito", "Producción escrita")
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun BarraLateral(onVolver: () -> Unit) {
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
            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.GridView, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
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
                Box(contentAlignment = Alignment.Center) { Text("MG", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun TarjetaAsignatura() {
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Color(0xFFE2E8F0)), color = Color.White) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFEEF2FF), modifier = Modifier.size(48.dp)) {
                    Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.Book, null, tint = Color(0xFF4C63E6), modifier = Modifier.size(24.dp)) }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("ASIGNATURA", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8), letterSpacing = 1.sp)
                    Text("Lenguaje y Comunicación", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                }
            }
            Surface(shape = RoundedCornerShape(8.dp), border = BorderStroke(1.dp, Color(0xFFE2E8F0)), color = Color.White, modifier = Modifier.padding(start = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    Text("1.ª\nUnidad", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ChevronRight, null, tint = Color(0xFF0F172A), modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
fun TarjetaObjetivo(codigo: String, colorCodigo: Color, fondoCodigo: Color, titulo: String, subtitulo: String) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Color(0xFFE2E8F0)), color = Color.White) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = RoundedCornerShape(8.dp), color = fondoCodigo) {
                Text(codigo, color = colorCodigo, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(titulo, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), maxLines = 3, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(subtitulo, fontSize = 12.sp, color = Color(0xFF94A3B8))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(shape = RoundedCornerShape(8.dp), color = Color(0xFFF8FAFC)) {
                Icon(Icons.Default.ChevronRight, null, tint = Color(0xFF64748B), modifier = Modifier.padding(8.dp).size(20.dp))
            }
        }
    }
}