package com.example.sparta_team_searchyoutubedata.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparta_team_searchyoutubedata.databinding.FragmentSearchBinding
import com.example.sparta_team_searchyoutubedata.myVideoFragment.MainViewModel
import com.example.sparta_team_searchyoutubedata.myVideoFragment.MainViewModelFactory
import com.example.sparta_team_searchyoutubedata.room.database.MyVideoListDatabase
import com.example.sparta_team_searchyoutubedata.room.database.WatchedListDatabase
import com.example.sparta_team_searchyoutubedata.room.entity.WatchedListEntity
import com.example.sparta_team_searchyoutubedata.room.repository.MyVideoListRepository
import com.example.sparta_team_searchyoutubedata.room.repository.WatchedListRepository
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val listAdapter: SearchListAdapter by lazy {
        SearchListAdapter{item ->
            onItemClick(item)}
    }

    private val viewModel: SearchViewModel by viewModels{
        SearchViewModelFactory()
    }

    private lateinit var mainViewModel: MainViewModel



    private var searchKey: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepository()
        initView()
        initViewModel()
    }

    private fun initView() = with(binding){
        ivSearch.setOnClickListener {
            // 키보드를 숨기기
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)

            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                searchKey = etSearch.text.toString()
                viewModel.onSearch(searchKey, "")
            } else {
                Toast.makeText(context, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }






        rvSearchResult.adapter = listAdapter
        rvSearchResult.layoutManager = LinearLayoutManager(requireContext())

        val layoutManager = LinearLayoutManager(context)
        rvSearchResult.layoutManager = layoutManager

        rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && !viewModel.uiState.value.isLoading) {
                    // 사용자가 RecyclerView의 마지막을 드래그한 경우
                    viewModel.onSearch(searchKey, "next")
                }

                if (!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE && !viewModel.uiState.value.isLoading) {
                    // 사용자가 RecyclerView의 최상단을 드래그한 경우
                    viewModel.onSearch(searchKey, "prev")
                }
            }
        })
    }

    private fun initViewModel(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }
    }

    private fun onBind(state: SearchUiState){
        listAdapter.submitList(state.list)
        binding.progress.isVisible = state.isLoading
    }

    private fun onItemClick(item: SearchItem) {
        val watchedVideo = WatchedListEntity(
            id = item.thumbnail,
            title = item.title,
            thumbnailUrl = item.thumbnail,
            description = item.description,
            isLiked = item.isLiked
        )

        mainViewModel.insertWatchedVideo(watchedVideo)
    }

    private fun initRepository() {
        val myVideoDatabase = MyVideoListDatabase.getMyVideoDatabase(requireContext())
        val watchedDatabase = WatchedListDatabase.getDataBase(requireContext())

        val myVideoRepository = MyVideoListRepository(myVideoDatabase.myVideoListDao())
        val watchedListRepository = WatchedListRepository(watchedDatabase.watchedListDao())

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(myVideoRepository, watchedListRepository)
        )[MainViewModel::class.java]
    }
}