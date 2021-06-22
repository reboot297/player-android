package com.reboot297.player.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


class MainActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playerView = findViewById<PlayerView>(R.id.playerView)
        player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.openVideo -> {
                openVideoDialog()
            }

        }
        return true
    }

    private fun openVideoDialog() {
        val editText = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_open_media_title)
            .setView(editText)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                openVideo(editText.text.toString())
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
        dialog.show()
    }

    private fun openVideo(url: String) {
        val mediaItem: MediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }


}