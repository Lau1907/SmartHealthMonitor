package mx.utng.smarthealthmonitor.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

/**
 * Pantalla de reproducción de video con ExoPlayer.
 * ExoPlayer no tiene Composable nativo, se integra con AndroidView
 * envolviendo un PlayerView del View system.
 */
@Composable
fun TvPlaybackScreen(navController: NavController) {
    val ctx = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(ctx).build().apply {
            val mediaItem = MediaItem.fromUri(
                "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4"
            )
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    // CRÍTICO: liberar ExoPlayer al salir del Composable
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    Box(Modifier.fillMaxSize().background(Color.Black)) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Surface(
            onClick = {
                exoPlayer.stop()
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color(0x88000000),
                focusedContainerColor = Color(0xCCFFFFFF)
            )
        ) {
            Text(
                "← Volver",
                color = Color.White,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

// Commit sugerido: feat(tv): add TvPlaybackScreen with ExoPlayer via AndroidView and DisposableEffect
// Tag sugerido: git tag -a v2.1.0 -m 'feat: TV detail and playback screens with ExoPlayer'