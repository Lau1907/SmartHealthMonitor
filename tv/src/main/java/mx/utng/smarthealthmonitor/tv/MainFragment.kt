package mx.utng.smarthealthmonitor.tv

import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.core.content.ContextCompat

class MainFragment : BrowseSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title        = "SmartHealth TV"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        brandColor = ContextCompat.getColor(requireContext(), R.color.sh_primary)

        cargarFilas()
    }

    private fun cargarFilas() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // ── Fila 1: Estado actual ────────────────────────
        val estadoAdapter = ArrayObjectAdapter(FCCardPresenter())
        estadoAdapter.add(LecturaFC(id = 0, valorBpm = 88, hora = "Ahora"))
        estadoAdapter.add(LecturaFC(id = 1, valorBpm = 4250, hora = "Pasos"))
        rowsAdapter.add(ListRow(HeaderItem("Estado actual"), estadoAdapter))

        // ── Fila 2: Historial de FC ──────────────────────
        val histAdapter = ArrayObjectAdapter(FCCardPresenter())
        MockData.historialFC.forEach { histAdapter.add(it) }
        rowsAdapter.add(ListRow(HeaderItem("Historial FC"), histAdapter))

        adapter = rowsAdapter
    }
}