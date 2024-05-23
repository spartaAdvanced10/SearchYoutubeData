package com.example.sparta_team_searchyoutubedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sparta_team_searchyoutubedata.databinding.ActivityMainBinding
import com.example.sparta_team_searchyoutubedata.homeFragment.HomeFragment
import com.example.sparta_team_searchyoutubedata.myVideoFragment.MyVideoFragment
import com.example.sparta_team_searchyoutubedata.network.client.RetrofitClient
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepository
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import com.example.sparta_team_searchyoutubedata.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        initView()
    }


    private fun initView() = with(binding) {
        setSupportActionBar(binding.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        botNaviMain.itemIconTintList = null
        botNaviMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.botNavi_menu_home -> {
                    setFragment(HomeFragment())
                    true
                }

                R.id.botNavi_menu_mine -> {
                    setFragment(MyVideoFragment())
                    true
                }

                R.id.botNavi_menu_search -> {
                    setFragment(SearchFragment())
                    true
                }

                else -> false
            }
        }

        botNaviMain.selectedItemId = R.id.botNavi_menu_home
    }

    private fun setBotNaviIcon(onIcon: String) = with(binding) {
        botNaviMain.menu.findItem(R.id.botNavi_menu_home).setIcon(R.drawable.ic_home_off)
        botNaviMain.menu.findItem(R.id.botNavi_menu_mine).setIcon(R.drawable.ic_mine_off)
        botNaviMain.menu.findItem(R.id.botNavi_menu_search).setIcon(R.drawable.ic_search_off)

        when (onIcon) {
            "home" -> botNaviMain.menu.findItem(R.id.botNavi_menu_home)
                .setIcon(R.drawable.ic_home_on)

            "mine" -> botNaviMain.menu.findItem(R.id.botNavi_menu_mine)
                .setIcon(R.drawable.ic_mine_on)

            "search" -> botNaviMain.menu.findItem(R.id.botNavi_menu_search)
                .setIcon(R.drawable.ic_search_on)
            else -> {}
        }
    }

    private fun setFragment(fragment: Fragment) {
        this@MainActivity.supportFragmentManager.beginTransaction()
            .replace(binding.flMain.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}


//lifecycleScope.launch {
//    val resultSearch: List<String>? = youtubeDataRepository.getSearch(
//        order = "relevance",
//        q = "android"
//    ).items?.map {
//        it.snippet?.thumbnails?.medium?.url ?: ""
//    }
//
////            val resultChannel = youtubeDataRepository.getChannel(
////                id = "UCVHFbqXqoYvEWM1Dxl0QDg"
////            ).items
////
////            val resultVideoCategory = youtubeDataRepository.getVideoCategory().items
////
////            val resultVideo = youtubeDataRepository.getVideos(videoCategoryId = "0", chart = "mostPopular").items
//
//    if (!resultSearch.isNullOrEmpty()) {
//        Log.d("test", resultSearch.toString())
//    }
//}


