package com.example.sparta_team_searchyoutubedata.search

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparta_team_searchyoutubedata.databinding.FragmentSearchBinding
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val listAdapter: SearchListAdapter by lazy {
        SearchListAdapter()
    }

    private val viewModel: SearchViewModel by viewModels{
        SearchViewModelFactory()
    }


    private var receivedData: SearchItem? = null

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

        initView()
        initViewModel()
    }

    private fun initView() = with(binding){
        ivSearch.setOnClickListener {
            searchKey = etSearch.text.toString()
            viewModel.onSearch(searchKey, "")
        }

        rvSearchResult.adapter = listAdapter
        rvSearchResult.layoutManager = LinearLayoutManager(requireContext())

        val layoutManager = LinearLayoutManager(context)
        rvSearchResult.layoutManager = layoutManager

        rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 사용자가 RecyclerView의 마지막을 드래그한 경우
                    viewModel.onSearch(searchKey, "next")
                }

                if (!recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
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
}