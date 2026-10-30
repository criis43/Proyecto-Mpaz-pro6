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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class AsignaturaEstudiante(
    val id: Int,
    val nivel: String,
    val nombre: String,
    val unidad: String,
    val progreso: Float,
    val actividadesTexto: String,
    val icono: ImageVector,
    val colorAccent: Color,
    val fondoIcono: Color
)

@Composable
fun PantallaEstudiante(onVolver: () -> Unit = {}) {
    val asignaturas = remember {
        listOf(
            AsignaturaEstudiante(1, "6.º BÁSICO", "Lenguaje", "El poder de las historias", 0.68f, "4 de 6 actividades", Icons.Default.Book, Color(0xFFDC2626), Color(0xFFFEE2E2)),
            AsignaturaEstudiante(2, "6.º BÁSICO", "Matemática", "Números y operaciones", 0.42f, "3 de 7 actividades", Icons.Default.Calculate, Color(0xFF2563EB), Color(0xFFDBEAFE)),
            AsignaturaEstudiante(3, "6.º BÁSICO", "Ciencias Naturales", "La energía que nos rodea", 0.25f, "2 de 8 actividades", Icons.Default.Science, Color(0xFF16A34A), Color(0xFFDCFCE7)),
            AsignaturaEstudiante(4, "6.º BÁSICO", "Historia", "Chile en el siglo XX", 0.10f, "1 de 6 actividades", Icons.Default.History, Color(0xFFD97706), Color(0xFFFEF3C7))
        )
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
    ) {
        // Barra Lateral
        BarraLateralEstudiante(onVolver = onVolver)

        // Contenido Principal
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "MI APRENDIZAJE", color = Color(0xFF4C63E6), fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "¡Hola, Tomás!", color = Color(0xFF0F172A), fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "¿Qué quieres aprender hoy?", color = Color(0xFF64748B), fontSize = 16.sp)

            Spacer(modifier = Modifier.height(28.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(text = "Mis asignaturas", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Elige una para continuar aprendiendo", fontSize = 13.sp, color = Color(0xFF64748B))
                }
                Text(text = "1.er semestre", fontSize = 13.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))


            asignaturas.forEach { item ->
                TarjetaAsignaturaEstudiante(item)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun TarjetaAsignaturaEstudiante(item: AsignaturaEstudiante) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        color = Color.White
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {

            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(item.colorAccent)
            )

            Column(modifier = Modifier.padding(20.dp).weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = item.fondoIcono,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(item.icono, contentDescription = null, tint = item.colorAccent, modifier = Modifier.size(24.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = item.nivel, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8), letterSpacing = 1.sp)
                        Text(text = item.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                        Text(text = item.unidad, fontSize = 13.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Progreso %
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Progreso", fontSize = 12.sp, color = Color(0xFF64748B))
                    Text(text = "${(item.progreso * 100).toInt()}%", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                }

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = { item.progreso },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF1E293B),
                    trackColor = Color(0xFFF1F5F9)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = item.actividadesTexto, fontSize = 12.sp, color = Color(0xFF94A3B8))

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Text(
                        text = "Continuar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = item.colorAccent
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = item.colorAccent,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BarraLateralEstudiante(onVolver: () -> Unit) {
    var fotoPerfilUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> fotoPerfilUri = uri }

    Column(
        modifier = Modifier.width(80.dp).fillMaxHeight().background(Color(0xFF1E293B)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // LOGO DE LA APP
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
            Box(contentAlignment = Alignment.Center) { Icon(Icons.Default.School, null, tint = Color.White, modifier = Modifier.size(24.dp)) }
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
                Box(contentAlignment = Alignment.Center) { Text("TS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
    }
}
