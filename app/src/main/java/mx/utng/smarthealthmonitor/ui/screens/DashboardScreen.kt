// ui/screens/DashboardScreen.kt
package mx.utng.smarthealthmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.utng.smarthealthmonitor.data.models.LecturaFC
import mx.utng.smarthealthmonitor.data.models.MockData
import mx.utng.smarthealthmonitor.ui.components.FilaHistorial
import mx.utng.smarthealthmonitor.ui.components.TarjetaDato
import mx.utng.smarthealthmonitor.ui.theme.SmartHealthMonitorTheme
import mx.utng.smarthealthmonitor.ui.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onHistorialClick: () -> Unit = {},
    onAlertClick: () -> Unit = {},
    viewModel: DashboardViewModel = viewModel()  // ← inyección automática
) {
    // collectAsState() convierte StateFlow en State de Compose
    val fc       by viewModel.fc.collectAsState()
    val pasos    by viewModel.pasos.collectAsState()
    val historial = viewModel.historial

    SmartHealthMonitorTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "SmartHealth",
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor    = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick        = onAlertClick,
                    containerColor = MaterialTheme.colorScheme.error
                ) {
                    Icon(
                        imageVector        = Icons.Default.Warning,
                        contentDescription = "Enviar alerta de emergencia",
                        tint               = MaterialTheme.colorScheme.onError
                    )
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding      = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    TarjetaDato(
                        valor      = "$fc",
                        unidad     = "bpm",
                        label      = "Frecuencia cardíaca",
                        colorValor = MaterialTheme.colorScheme.error
                    )
                }
                item {
                    TarjetaDato(
                        valor      = "%,d".format(pasos),
                        unidad     = "pasos",
                        label      = "Pasos del día",
                        colorValor = MaterialTheme.colorScheme.primary
                    )
                }
                item {
                    Row(
                        modifier              = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Text(
                            "Historial reciente",
                            style = MaterialTheme.typography.titleMedium
                        )
                        TextButton(onClick = onHistorialClick) {
                            Text("Ver todo")
                        }
                    }
                }
                items(historial, key = { it.id }) { lectura ->
                    FilaHistorial(lectura = lectura)
                }
            }
        }
    }
}

// ── Preview usa MockData directamente (no ViewModel) ──────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Dashboard - Light",
    showSystemUi = true, device = "id:pixel_6")
@Preview(showBackground = true, name = "Dashboard - Dark",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DashboardScreenPreview() {
    SmartHealthMonitorTheme {
        // Preview no puede usar viewModel(), usamos una versión con parámetros directos
        PreviewDashboardContent(
            fc        = MockData.fcActual,
            pasos     = MockData.pasosActual,
            historial = MockData.historialFC
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewDashboardContent(
    fc: Int,
    pasos: Int,
    historial: List<LecturaFC>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SmartHealth", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(containerColor = MaterialTheme.colorScheme.error, onClick = {}) {
                Icon(Icons.Default.Warning, contentDescription = null,
                    tint = MaterialTheme.colorScheme.onError)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier            = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding      = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                TarjetaDato("$fc", "bpm", "Frecuencia cardíaca",
                    MaterialTheme.colorScheme.error)
            }
            item {
                TarjetaDato("%,d".format(pasos), "pasos", "Pasos del día",
                    MaterialTheme.colorScheme.primary)
            }
            item {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text("Historial reciente", style = MaterialTheme.typography.titleMedium)
                    TextButton(onClick = {}) { Text("Ver todo") }
                }
            }
            items(historial, key = { it.id }) { FilaHistorial(lectura = it) }
        }
    }
}