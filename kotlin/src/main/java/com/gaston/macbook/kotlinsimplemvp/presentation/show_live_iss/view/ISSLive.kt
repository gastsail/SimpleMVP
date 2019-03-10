package com.gaston.macbook.kotlinsimplemvp.presentation.show_live_iss.view

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.gaston.macbook.kotlinsimplemvp.BuildConfig
import com.gaston.macbook.kotlinsimplemvp.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_isslive.*

class ISSLive : YouTubeBaseActivity() {

    companion object {
        const val VIDEO_ID = "dVTzaXmYm_s"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_isslive)
        initVideo()
    }

    private fun initVideo() {
        isslive_youtube_player.initialize(BuildConfig.YoutubeApiKey, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                if (!b) {
                    youTubePlayer.loadVideo(ISSLive.VIDEO_ID)
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Toast.makeText(this@ISSLive, getString(R.string.youtube_video_load_failed), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
