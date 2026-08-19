package mx.utng.smarthealthmonitor.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class TvViewModel : ViewModel() {

    private val _fc = MutableStateFlow(0)
    val fc: StateFlow<Int> = _fc.asStateFlow()

    private val _historial = MutableStateFlow<List<LecturaFC>>(emptyList())
    val historial: StateFlow<List<LecturaFC>> = _historial.asStateFlow()

    // Card de "Estado actual" que se muestra en la primera fila del catálogo.
    // Se mantiene igual que en MainFragment (id=0, valorBpm=88, hora="Ahora").
    private val _estadoActual = MutableStateFlow(
        LecturaFC(id = 0, valorBpm = 88, hora = "Ahora")
    )
    val estadoActual: StateFlow<LecturaFC> = _estadoActual.asStateFlow()

    // NUEVO (Sesión 12): lista combinada estado actual + historial, indexable por id.
    // TvDetailScreen la usa para encontrar la lectura seleccionada sin importar
    // de qué fila del catálogo vino la card.
    val todasLasLecturas: StateFlow<List<LecturaFC>> =
        combine(_estadoActual, _historial) { estado, historial ->
            listOf(estado) + historial
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            listOf(_estadoActual.value)
        )

    init {
        _historial.value = MockData.historialFC
    }
}