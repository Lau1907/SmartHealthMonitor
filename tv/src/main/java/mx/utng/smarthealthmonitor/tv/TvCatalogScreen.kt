package mx.utng.smarthealthmonitor.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface

/**
 * Catálogo en Compose for TV. Replica MainFragment (BrowseSupportFragment):
 *  - Fila "Estado actual": la card de FC en vivo (LecturaFC id=0).
 *  - Fila "Historial FC": historial desde TvViewModel (mismo dato que en Leanback).
 * Mismos colores que FCCardPresenter: azul (#1B4F8A) si esNormal, rojo (#B3261E) si no.
 */
@Composable
fun TvCatalogScreen(
    onCardClick: (Int) -> Unit,
    viewModel: TvViewModel = viewModel()
) {
    val estadoActual by viewModel.estadoActual.collectAsStateWithLifecycle()
    val historial by viewModel.historial.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1117)) // sh_dark
            .padding(top = 32.dp, start = 48.dp)
    ) {
        Text(
            "SmartHealth TV",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        // ── Fila 1: Estado actual ──────────────────────────
        FcRow(
            titulo = "Estado actual",
            lecturas = listOf(estadoActual),
            onCardClick = onCardClick
        )

        // ── Fila 2: Historial FC ───────────────────────────
        FcRow(
            titulo = "Historial FC",
            lecturas = historial,
            onCardClick = onCardClick
        )
    }
}

@Composable
private fun FcRow(
    titulo: String,
    lecturas: List<LecturaFC>,
    onCardClick: (Int) -> Unit
) {
    Column(Modifier.padding(top = 24.dp)) {
        Text(
            titulo,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White.copy(alpha = 0.85f)
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lecturas, key = { it.id }) { lectura ->
                FcCard(lectura = lectura, onClick = { onCardClick(lectura.id) })
            }
        }
    }
}

@Composable
private fun FcCard(lectura: LecturaFC, onClick: () -> Unit) {
    // Mismos colores que FCCardPresenter (Leanback): #1B4F8A normal, #B3261E fuera de rango
    val bgColor = if (lectura.esNormal) Color(0xFF1B4F8A) else Color(0xFFB3261E)

    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(240.dp)
            .height(180.dp),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = bgColor,
            focusedContainerColor = bgColor.copy(alpha = 0.8f)
        ),
        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp))
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Column(Modifier.padding(12.dp)) {
                Text(
                    "${lectura.valorBpm} bpm",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    lectura.hora,
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// Commit sugerido: feat(tv): add TvCatalogScreen (Compose for TV) replacing MainFragment rows