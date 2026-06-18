package mx.utng.smarthealthmonitor.wear.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WearDashboardViewModel : ViewModel() {

    private val _fc = MutableStateFlow(72)
    val fc: StateFlow<Int> = _fc.asStateFlow()

    // ← NUEVO: historial simulado (se conectará al Repository real más adelante)
    private val _historial = MutableStateFlow(
        listOf(
            LecturaFC(id = 1, valorBpm = 75, hora = "10:30", esNormal = true),
            LecturaFC(id = 2, valorBpm = 110, hora = "10:45", esNormal = false),
            LecturaFC(id = 3, valorBpm = 80, hora = "11:00", esNormal = true)
        )
    )
    val historial: StateFlow<List<LecturaFC>> = _historial.asStateFlow()

    init {
        viewModelScope.launch {
            // Simulación de FC — se conectará al Repository en pasos siguientes
        }
    }
}