package com.example.sparta_team_searchyoutubedata.videoDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.ActivityVideoDetailBinding
import com.example.sparta_team_searchyoutubedata.homeFragment.HomeItemModel

class VideoDetailActivity : AppCompatActivity() {
    private val binding: ActivityVideoDetailBinding by lazy {
        ActivityVideoDetailBinding.inflate(layoutInflater)
    }
    private val videoDetailItem: VideoDetailItem by lazy {
        VideoDetailItem("", "title", "description")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        initView()
    }

    private fun initView() = with(binding){
        setSupportActionBar(tbVideoDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        videoDetailItem?.let {
            ivThumbnail.load(it.thumbnail)
            tvTitle.text = it.title
            tvDetail.text = it.description
        }
        //intent 받는 부분
        val intent = intent
        val item = intent.getSerializableExtra("item") as HomeItemModel?

        if (item != null) {
            binding.tvTitle.text = item.title
            binding.ivThumbnail.load(item.thumbnails)
            binding.tvDetail.text = item.description
        }

        ivIcGood.setOnClickListener {  }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_videodetail_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.toolbar_videoDetail_upload -> {
                return true
            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }
}