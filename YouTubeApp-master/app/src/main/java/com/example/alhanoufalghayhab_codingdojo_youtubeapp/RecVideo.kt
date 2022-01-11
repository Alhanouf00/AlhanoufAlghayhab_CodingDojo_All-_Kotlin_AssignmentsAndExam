package com.example.alhanoufalghayhab_codingdojo_youtubeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alhanoufalghayhab_codingdojo_youtubeapp.databinding.RecVideoViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class RecVideo(val listVideo: MutableMap<String, String>,val player: YouTubePlayer) : RecyclerView.Adapter<RecVideo.RecVideoView>() {
    inner class RecVideoView(val Binding: RecVideoViewBinding) : RecyclerView.ViewHolder(Binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecVideoView {
       return RecVideoView(RecVideoViewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }


    override fun onBindViewHolder(holder: RecVideoView, position: Int) {
        val listTitleOfVideoPlayer = ArrayList<String>()
        val listCodeOfVideoPlayer = ArrayList<String>()




        holder.Binding.apply {
            for (key in listVideo.keys) {
                listTitleOfVideoPlayer.add(key)


            }
            title.text =listTitleOfVideoPlayer[position]


            for (value in listVideo.values)
            {
                listCodeOfVideoPlayer.add(value)

            }

            cardvideo.setOnClickListener {
                player.loadVideo(listCodeOfVideoPlayer[position],0F)
            }




        }
    }


    override fun getItemCount(): Int {
       return listVideo.size
    }
}