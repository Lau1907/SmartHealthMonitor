package mx.utng.smarthealthmonitor.data.models

import mx.utng.smarthealthmonitor.data.db.LecturaFC

object MockData {
    val historialFC = listOf(
        LecturaFC(valorBpm = 78, hora = "11:00"),
        LecturaFC(valorBpm = 82, hora = "10:30"),
        LecturaFC(valorBpm = 76, hora = "10:00"),
        LecturaFC(valorBpm = 95, hora = "09:30"),
        LecturaFC(valorBpm = 71, hora = "09:00"),
        LecturaFC(valorBpm = 80, hora = "08:30"),
        LecturaFC(valorBpm = 74, hora = "08:00")
    )
    var fcActual = 78
    var pasosActual = 4250
}