package com.example.sparta_team_searchyoutubedata.myVideo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.adapter.MyVideoListAdapter
import com.example.sparta_team_searchyoutubedata.adapter.WatchedListAdapter
import com.example.sparta_team_searchyoutubedata.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyVideoBinding.inflate(inflater, container, false)

        Glide.with(binding.root)
            .load(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(binding.ivProfileImg)


        initAdapter()

        return binding.root
    }

    private fun initAdapter(){
        val watchedListAdapter = WatchedListAdapter()
        val myVideoListAdapter = MyVideoListAdapter()

        binding.rvWatchingRecord.adapter = watchedListAdapter
        binding.rvWatchingRecord.layoutManager = LinearLayoutManager(requireContext())

        binding.rvMyVideo.adapter = myVideoListAdapter
        binding.rvMyVideo.layoutManager = LinearLayoutManager(requireContext())


    }

}