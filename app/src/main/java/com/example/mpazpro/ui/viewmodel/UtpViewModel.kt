package com.example.mpazpro.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Science
import androidx.lifecycle.ViewModel
import com.example.mpazpro.ui.pantallas.EstadoMaterial
import com.example.mpazpro.ui.pantallas.MaterialAcademico
import com.example.mpazpro.util.NotificacionesHelper
import com.example.mpazpro.util.PreferenciasHelper

class UtpViewModel : ViewModel() {


    val listaMateriales = mutableStateListOf<MaterialAcademico>()

    fun cargarMateriales(contexto: Context) {
        if (listaMateriales.isNotEmpty()) return // Evita recargar si ya está en memoria

        val base = listOf(
            MaterialAcademico(1, "LENGUAJE Y COMUNICACIÓN", "Unidad 1 · El poder de las historias", "Contenido y 5 actividades Por María González Hoy, 09:42", Icons.Default.Book, Color(0xFF4C63E6), Color(0xFFEEF2FF)),
            MaterialAcademico(2, "MATEMÁTICA", "Unidad 1 · Números y operaciones", "Contenido y 6 actividades Por Felipe Araya Ayer, 16:18", Icons.Default.Calculate, Color(0xFF2563EB), Color(0xFFDBEAFE)),
            MaterialAcademico(3, "CIENCIAS NATURALES", "Unidad 1 · La energía", "Contenido y 4 actividades Por Paula Méndez 12 may, 11:05", Icons.Default.Science, Color(0xFF16A34A), Color(0xFFDCFCE7)),
            MaterialAcademico(4, "HISTORIA, GEOGRAFÍA Y CS. SOCIALES", "Unidad 1 · Chile en el siglo XX", "Contenido y 5 actividades Por Jorge Fuentes 10 may, 14:30", Icons.Default.History, Color(0xFFD97706), Color(0xFFFEF3C7))
        )

        base.forEach { material ->
            val estadoGuardadoStr = PreferenciasHelper.obtenerEstadoMaterial(contexto, material.id)
            val estadoGuardado = try {
                EstadoMaterial.valueOf(estadoGuardadoStr)
            } catch (e: Exception) {
                EstadoMaterial.PENDIENTE
            }
            listaMateriales.add(material.copy(estado = estadoGuardado))
        }
    }

    fun cambiarEstado(contexto: Context, materialId: Int, nuevoEstado: EstadoMaterial) {
        val index = listaMateriales.indexOfFirst { it.id == materialId }
        if (index != -1) {
            val material = listaMateriales[index]
            listaMateriales[index] = material.copy(estado = nuevoEstado)

            // Persistencia
            PreferenciasHelper.guardarEstadoMaterial(contexto, materialId, nuevoEstado.name)

            // Notificación Nativa
            val titulo = if (nuevoEstado == EstadoMaterial.APROBADO) "Material Aprobado" else "Material Rechazado"
            val mensaje = if (nuevoEstado == EstadoMaterial.APROBADO) "Has autorizado: ${material.titulo}" else "Se ha rechazado: ${material.titulo}"
            NotificacionesHelper.enviarNotificacion(contexto, titulo, mensaje)
        }
    }
}