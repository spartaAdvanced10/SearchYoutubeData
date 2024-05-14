package com.example.sparta_team_searchyoutubedata

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sparta_team_searchyoutubedata.databinding.ActivityMainBinding
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepository
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val youtubeDataRepository: YoutubeDataRepository by lazy {
        YoutubeDataRepositoryImpl(RetrofitClient.youtubeDataRemote)
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        lifecycleScope.launch {
//            val resultSearch = youtubeDataRepository.getSearch(
//                order = "relevance",
//                q = "android"
//            ).items?.map{
//                it.id?.channelId
//            }
//
//            val resultChannel = youtubeDataRepository.getChannel(
//                id = "UCVHFbqXqoYvEWM1Dxl0QDg"
//            ).items
//
//            val resultVideoCategory = youtubeDataRepository.getVideoCategory().items

            val resultVideo = youtubeDataRepository.getVideos(videoCategoryId = "0", chart = "mostPopular").items

            binding.tvTest.text = resultVideo.toString()

        }
    }
}