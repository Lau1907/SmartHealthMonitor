# SmartHealth Monitor

Aplicación Android multiplataforma para monitoreo de salud personal.
Desarrollada como proyecto integrador en UTNG — 9° Cuatrimestre 2025.

## Stack tecnológico
- Kotlin + Jetpack Compose
- Material Design 3
- Wearable Data Layer API (Wear OS)
- Android TV / Leanback + Media3
- Jetpack Navigation + Room + StateFlow

## Pantallas implementadas
- [x] LoginScreen — S4
- [x] DashboardScreen — S5
- [ ] Historial + wearable real — S6
- [ ] Android TV — S10-S12

## Unidad II — Wear OS
| Pantalla | Descripción |
|---|---|
| WearDashboardScreen | FC en tiempo real con ScalingLazyColumn y TimeText |
| WearHistorialScreen | Lista con Rotary Input (corona del reloj) |
| WearAlertaScreen    | Botones circulares de confirmación |
| SmartHealth WatchFace | Hora + FC en el WatchFace nativo |

|screenshots/watchface..jpeg y wear_dashboard.jpeg|
<img width="837" height="407" alt="image" src="https://github.com/user-attachments/assets/97edfbd6-74cb-4f88-82c3-35ddb4bef1ab" />

## Arquitectura — SmartHealth Monitor

```
Sensor PPG (Wear OS)
      │ Health Services API
      ▼
PassiveListenerService (wear)
      │ MessageClient (BLE)
      ▼
WearListenerService (app)
      │ SmartHealthRepository
      ▼
StateFlow<Int> (fcActual) ──────────────────────────────────┐
      │                                                      │
      ▼                                                      ▼
DashboardViewModel (app)                              TvViewModel (tv)
      │ collectAsState()                                     │ collectAsState()
      ▼                                                      ▼
DashboardScreen (Compose)                          TvCatalogScreen (Compose TV)
      └── CastButton ──► Chromecast (Remote Playback)

Room DB (LecturaFC) ◄── Repository ──► Flow<List<LecturaFC>>
      │
      ┌─────────────────────┴──────────┐
      ▼                                 ▼
HistorialScreen (app)            TvCatalogScreen (tv)
```

## Autor
Vanesa Monserrat Medrano Hernández — UTNG — vanehernan464775@gmail.com
Laura Berenice Tapia Cid — UTNG — lauraberenicetapiacid@gmail.com
