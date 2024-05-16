package com.example.sparta_team_searchyoutubedata.videoDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.ActivityVideoDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VideoDetailActivity : AppCompatActivity() {
    private val binding: ActivityVideoDetailBinding by lazy {
        ActivityVideoDetailBinding.inflate(layoutInflater)
    }
    private val videoDetailItem: VideoDetailItem by lazy {
        VideoDetailItem("", "title", "description", false)
    }

    private val viewModel: VideoDetailViewModel by viewModels{
        VideoDetailViewModelFactory(videoDetailItem)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        initView()
        initViewModel()
    }

    private fun initView() = with(binding){
        setSupportActionBar(tbVideoDetail)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel?.uiState?.value?.let {
            ivThumbnail.load(it.thumbnail)
            tvTitle.text = it.title
            tvDetail.text = it.description
            if(it.isLiked) ivIcGood.setImageResource(R.drawable.ic_like_on)
            else ivIcGood.setImageResource(R.drawable.ic_like_off)
        }

        ivIcGood.setOnClickListener { viewModel.onLiked() }
    }

    private fun initViewModel() = with(viewModel){
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }
    }

    private fun onBind(state: VideoDetailItem) = with(binding){
        if(state.isLiked) ivIcGood.setImageResource(R.drawable.ic_like_on)
        else ivIcGood.setImageResource(R.drawable.ic_like_off)
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