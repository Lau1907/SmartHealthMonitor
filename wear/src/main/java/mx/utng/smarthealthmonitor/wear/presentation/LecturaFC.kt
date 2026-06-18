package mx.utng.smarthealthmonitor.wear.presentation

data class LecturaFC(
    val id: Long,
    val valorBpm: Int,
    val hora: String,
    val esNormal: Boolean
)