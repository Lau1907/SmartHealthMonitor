package mx.utng.smarthealthmonitor.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class TvViewModel : ViewModel() {

    private val _fc = MutableStateFlow(0)
    val fc: StateFlow<Int> = _fc.asStateFlow()

    private val _historial = MutableStateFlow<List<LecturaFC>>(emptyList())
    val historial: StateFlow<List<LecturaFC>> = _historial.asStateFlow()

    init {
        _historial.value = MockData.historialFC
    }
}