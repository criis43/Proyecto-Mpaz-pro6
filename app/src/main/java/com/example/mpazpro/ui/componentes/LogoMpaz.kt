package com.example.mpazpro.ui.componentes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LogoMpaz(
    modifier: Modifier = Modifier.size(24.dp),
    tint: Color = Color(0xFF4C63E6)
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        fun sx(x: Float) = (x / 100f) * w
        fun sy(y: Float) = (y / 100f) * h

        val colorContraste = if (tint == Color.White) Color(0xFF4C63E6) else Color.White

        // 1. LIBRO (Izquierda)
        val pathLibroBase = Path().apply {
            moveTo(sx(12f), sy(32f))
            lineTo(sx(38f), sy(32f))
            lineTo(sx(38f), sy(78f))
            quadraticTo(sx(25f), sy(82f), sx(12f), sy(78f))
            close()
        }
        drawPath(pathLibroBase, color = tint)

        val pathPaginaHoja = Path().apply {
            moveTo(sx(20f), sy(22f))
            quadraticTo(sx(32f), sy(25f), sx(38f), sy(32f))
            lineTo(sx(38f), sy(78f))
            quadraticTo(sx(28f), sy(74f), sx(20f), sy(70f))
            close()
        }
        drawPath(pathPaginaHoja, color = tint)

        // 2. LÁPIZ (Centro)
        val pathLapiz = Path().apply {
            moveTo(sx(44f), sy(28f))
            quadraticTo(sx(50f), sy(22f), sx(56f), sy(28f))
            lineTo(sx(56f), sy(72f))
            lineTo(sx(50f), sy(88f)) // Punta
            lineTo(sx(44f), sy(72f))
            close()
        }
        drawPath(pathLapiz, color = tint)

        // Detalle interno del lápiz
        val pathLineaLapiz = Path().apply {
            moveTo(sx(50f), sy(26f))
            lineTo(sx(50f), sy(72f))
        }
        drawPath(pathLineaLapiz, color = colorContraste, style = Stroke(width = sx(2.5f)))

        // 3. ESCUELA (Derecha)
        val pathEscuela = Path().apply {
            moveTo(sx(60f), sy(38f))
            lineTo(sx(74f), sy(22f))
            lineTo(sx(88f), sy(38f))
            lineTo(sx(88f), sy(78f))
            quadraticTo(sx(74f), sy(82f), sx(60f), sy(78f))
            close()
        }
        drawPath(pathEscuela, color = tint)

        // Puerta en arco
        val pathPuerta = Path().apply {
            moveTo(sx(66f), sy(78f))
            lineTo(sx(66f), sy(60f))
            quadraticTo(sx(71f), sy(54f), sx(76f), sy(60f))
            lineTo(sx(76f), sy(78f))
            close()
        }
        drawPath(pathPuerta, color = colorContraste)

        // Ventana circular superior
        drawCircle(
            color = colorContraste,
            radius = sx(3.5f),
            center = Offset(sx(74f), sy(42f))
        )

        // Ventanas cuadradas
        drawPath(Path().apply { addRect(Rect(sx(80f), sy(52f), sx(86f), sy(60f))) }, color = colorContraste)
        drawPath(Path().apply { addRect(Rect(sx(80f), sy(64f), sx(86f), sy(72f))) }, color = colorContraste)
    }
}
