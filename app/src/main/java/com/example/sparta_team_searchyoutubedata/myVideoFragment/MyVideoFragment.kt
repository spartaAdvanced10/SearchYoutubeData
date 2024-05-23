package com.example.sparta_team_searchyoutubedata.myVideoFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.FragmentMyVideoBinding
import com.example.sparta_team_searchyoutubedata.room.database.MyDatabase
import com.example.sparta_team_searchyoutubedata.room.database.MyVideoListDatabase
import com.example.sparta_team_searchyoutubedata.room.database.WatchedListDatabase
import com.example.sparta_team_searchyoutubedata.room.repository.MyRoomRepository
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import com.example.sparta_team_searchyoutubedata.room.repository.WatchedListRepository
import com.example.sparta_team_searchyoutubedata.ui.adapter.MyVideoListAdapter
import com.example.sparta_team_searchyoutubedata.ui.adapter.WatchedAdapter
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailActivity
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem

class MyVideoFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(MyRoomRepository(MyDatabase.getMyDatabase(requireContext()).myDao()))
    }

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyVideoBinding.inflate(inflater, container, false)

        Glide.with(binding.root)
            .load(R.drawable.profile)
            .circleCrop()
            .into(binding.ivProfileImg)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val watchedListAdapter = WatchedAdapter { video ->
            val intent = Intent(requireContext(), VideoDetailActivity::class.java).apply {
                putExtra(
                    "selectItem",
                    VideoDetailItem(
                        video.thumbnailUrl,
                        video.title,
                        video.description,
                        video.isLiked
                    )
                )
            }
            startActivity(intent)
        }

        val myVideoListAdapter = MyVideoListAdapter(
            onItemClick = { video ->
                val intent = Intent(requireContext(), VideoDetailActivity::class.java).apply {
                    putExtra(
                        "selectItem",
                        VideoDetailItem(
                            video.thumbnailUrl,
                            video.title,
                            video.description,
                            video.isLiked
                        )
                    )
                }
                Log.d("selectItem", "$intent")
                startActivity(intent)
            },
            onDeleteItemClick = { video ->
                mainViewModel.deleteMyVideo(video)

                //북마크 해제 메시지 추가
                Toast.makeText(context, "북마크가 해제되었습니다", Toast.LENGTH_SHORT).show()
            }
        )

        binding.rvWatchingRecord.adapter = watchedListAdapter
        binding.rvWatchingRecord.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mainViewModel.watchedVideosLiveData.observe(viewLifecycleOwner) { list ->
            watchedListAdapter.watchedList = list
            watchedListAdapter.notifyDataSetChanged()
        }

        binding.rvMyVideo.adapter = myVideoListAdapter
        binding.rvMyVideo.layoutManager =
            LinearLayoutManager(requireContext())
        mainViewModel.myVideoListData.observe(viewLifecycleOwner) { list ->
            myVideoListAdapter.myVideoList = list
            myVideoListAdapter.notifyDataSetChanged()
        }
    }

}