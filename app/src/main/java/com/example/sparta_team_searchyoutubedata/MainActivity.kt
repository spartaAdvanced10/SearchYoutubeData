package com.example.sparta_team_searchyoutubedata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.repository.SearchYoutubeDataRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch {
            val result = SearchYoutubeDataRepositoryImpl(RetrofitClient.search).getSearchImage(
                order = "relevance",
                q = "android"
            )
            findViewById<TextView>(R.id.tv_test).text = result.toString()
        }
    }
}