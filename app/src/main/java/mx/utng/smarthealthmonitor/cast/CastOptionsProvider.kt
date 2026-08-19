package mx.utng.smarthealthmonitor.cast

import android.content.Context
import com.google.android.gms.cast.CastMediaControlIntent
import com.google.android.gms.cast.framework.*
import com.google.android.gms.cast.framework.media.CastMediaOptions

/**
 * Configura el Cast SDK con el Web Receiver genérico de Google
 * (DEFAULT_MEDIA_RECEIVER_APPLICATION_ID) — no requiere App ID propio
 * para pruebas/desarrollo.
 */
class CastOptionsProvider : OptionsProvider {

    override fun getCastOptions(ctx: Context): CastOptions =
        CastOptions.Builder()
            .setReceiverApplicationId(
                CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID
            )
            .build()

    override fun getAdditionalSessionProviders(ctx: Context) =
        emptyList<SessionProvider>()
}

// Commit sugerido: feat(cast): add CastOptionsProvider and AndroidManifest meta-data