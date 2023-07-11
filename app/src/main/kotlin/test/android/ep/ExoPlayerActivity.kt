package test.android.ep

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
//import com.google.android.exoplayer2.ExoPlayer
//import com.google.android.exoplayer2.MediaItem
//import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
//import com.google.android.exoplayer2.source.hls.HlsMediaSource
//import com.google.android.exoplayer2.ui.StyledPlayerView
//import com.google.android.exoplayer2.upstream.DefaultDataSource
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import java.net.URL

internal class ExoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = requireNotNull(intent)
        val spec = intent.getStringExtra("spec")
//        val url = URL(spec)
        val context: Context = this
//        val dataSourceFactory = DefaultDataSourceFactory(context, BuildConfig.BUILD_TYPE)
        val mediaSourceFactory = DefaultMediaSourceFactory(context)
//        val mediaSourceFactory = HlsMediaSource.Factory(dataSourceFactory)
        val player = ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()
        val mediaItem = MediaItem.Builder()
            .setUri(spec)
            .setLiveConfiguration(
                MediaItem.LiveConfiguration.Builder().build()
            )
            .build()
        val root = FrameLayout(context).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            it.background = ColorDrawable(Color.BLACK)
        }
        PlayerView(context).also {
            it.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL
            )
            it.player = player
        }
        setContentView(root)
        root.post {
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }
}
