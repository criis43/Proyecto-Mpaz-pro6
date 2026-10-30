package com.example.mpazpro.util

import android.content.Context
import android.content.SharedPreferences

object PreferenciasHelper {
    private const val PREF_NAME = "mpaz_pro_local_db"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Guarda el estado de aprobación/rechazo de un material usando su ID
    fun guardarEstadoMaterial(context: Context, materialId: Int, estado: String) {
        getPrefs(context).edit().putString("material_estado_$materialId", estado).apply()
    }

    // Lee el estado guardado desde el almacenamiento
    fun obtenerEstadoMaterial(context: Context, materialId: Int): String {
        return getPrefs(context).getString("material_estado_$materialId", "PENDIENTE") ?: "PENDIENTE"
    }
}
