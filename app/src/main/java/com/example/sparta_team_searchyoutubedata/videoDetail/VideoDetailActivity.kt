package com.example.sparta_team_searchyoutubedata.videoDetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.ActivityVideoDetailBinding
import com.example.sparta_team_searchyoutubedata.room.database.MyVideoListDatabase
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VideoDetailActivity : AppCompatActivity() {
    private val binding: ActivityVideoDetailBinding by lazy {
        ActivityVideoDetailBinding.inflate(layoutInflater)
    }
    private val videoDetailItem: VideoDetailItem by lazy {
        intent.getSerializableExtra("selectItem") as  VideoDetailItem ?: VideoDetailItem("", "title", "description", false)
    }
    // repository 초기화 작업.
    private val repository: MyVideoListRepository by lazy{
        MyVideoListRepository(MyVideoListDatabase.getMyVideoDatabase(this).myVideoListDao())
    }
    private val viewModel: VideoDetailViewModel by viewModels{
        VideoDetailViewModelFactory(videoDetailItem, repository)
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

        ivIcGood.setOnClickListener {
            viewModel.onLiked()
            //토스트메시지 추가
            val message = if (viewModel.uiState.value?.isLiked == true) {
                "북마크가 해제되었습니다"
            } else {
                "북마크가 추가되었습니다"
            }
            Toast.makeText(this@VideoDetailActivity, message, Toast.LENGTH_SHORT).show()
        }
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
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, viewModel.uiState.value.thumbnail)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Share to other application")
                startActivity(shareIntent)
                return true
            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }
}