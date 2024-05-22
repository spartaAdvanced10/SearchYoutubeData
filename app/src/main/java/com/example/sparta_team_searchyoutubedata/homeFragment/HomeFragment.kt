package com.example.sparta_team_searchyoutubedata.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sparta_team_searchyoutubedata.R
import com.example.sparta_team_searchyoutubedata.databinding.FragmentHomeBinding
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment:Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter:HomeAdapter by lazy {
        HomeAdapter{item ->
            onItemClick(item)
        }
    }
    private val cAdapter:HomeAdapter by lazy {
        HomeAdapter{item ->
            onItemClick(item)
        }
    }


    private val channelAdapter:HomeAdapter by lazy {
        HomeAdapter{item ->
            onItemClick(item)
        }
    }
    private val viewModel:HomeViewModel by viewModels{
        HomeViewModelFactory(requireContext()   )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initView()
        initViewModel()
        initSpinner()



        return binding.root
    }

    private fun initView(){
        binding.rvPopular.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopular.adapter = adapter

        binding.rvCategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = cAdapter

        binding.rvChannels.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvChannels.adapter = channelAdapter

        viewModel.loadPopular()
    }

    private fun initViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }
    }
    private fun onBind(state : HomeUiState){
        adapter.submitList(state.list)
        cAdapter.submitList(state.listC)
        channelAdapter.submitList(state.listChannel)
    }
    private fun initSpinner(){
        binding.spHome.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.category, android.R.layout.simple_spinner_item)
        binding.spHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    //Gaming(20)
                    0 -> {
                        binding.tvCategories.setText("Gaming")
                        viewModel.loadCategory("20")
                    }
                    //Music(10)
                    1 -> {
                        binding.tvCategories.setText("Music")
                        viewModel.loadCategory("10")
                    }
                    //Pets/Animals(15)
                    2 -> {
                        binding.tvCategories.setText("Pets/Animals")
                        viewModel.loadCategory("15")
                    }
                    //Sport(17)
                    3 -> {
                        binding.tvCategories.setText("Sport")
                        viewModel.loadCategory("17")
                    }
                    //Movies(30)
                    else -> {
                        binding.tvCategories.setText("Movies")
                        viewModel.loadCategory("30")
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun onItemClick(item: HomeItemModel) {
        val watchedVideo = WatchedListEntity(
            id = item.thumbnails,
            title = item.title,
            thumbnailUrl = item.thumbnails,
            description = item.description,
            isLiked = item.isLiked
        )
        viewModel.saveWatchedVideo(watchedVideo)
    }
}