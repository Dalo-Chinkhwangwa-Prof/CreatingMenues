package com.bigbang.menuapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {


    lateinit var videoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoPath = "android.resource://$packageName/${R.raw.mov_bbb}"

        video_player.setOnPreparedListener{ mediaPlayer ->
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        }
        video_touch_interface.setOnClickListener { itemView ->

            Log.d("TAG_X", "Touched")
            if(video_player.isPlaying)
                video_player.pause()
            else
                video_player.resume()

        }
//        video_player.setMediaController(MediaController(this))


        menu_view.setOnClickListener { menuView ->

            val popupMenu = PopupMenu(this, menuView)
            val inflater = popupMenu.menuInflater
            inflater.inflate(R.menu.the_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.the_menu, menu)
        return true
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.display_video_item -> video_player.setVideoPath(videoPath)
            R.id.sonic_video -> video_player.setVideoPath("https://soniczone.net/Downloads/Video/Underground/E01.mp4")
            R.id.exit_menu_item -> exitProcess(0)//Exit application similar to System.exit(0);
            R.id.red_menu_item -> main_layout.setBackgroundColor(Color.RED)
            R.id.blue_menu_item -> main_layout.setBackgroundColor(Color.BLUE)
        }

        return true
    }
}
