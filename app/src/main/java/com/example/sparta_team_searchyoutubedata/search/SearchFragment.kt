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
import com.example.sparta_team_searchyoutubedata.databinding.FragmentSearchBinding
import com.example.sparta_team_searchyoutubedata.videoDetail.VideoDetailItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val listAdapter: SearchListAdapter by lazy {
        SearchListAdapter(startForActivity)
    }

    private val viewModel: SearchViewModel by viewModels{
        SearchViewModelFactory()
    }

    private val startForActivity by lazy {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getParcelableExtra<VideoDetailItem>("detailData")
                data?.let {
                    receivedData = SearchItem(data.thumbnail, data.title, data.description, data.isLiked)
                }
                Log.d("result.data", data.toString())
            }
        }
    }

    private var receivedData: SearchItem? = null

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
            viewModel.onSearch(etSearch.text.toString())
        }

        rvSearchResult.adapter = listAdapter
        rvSearchResult.layoutManager = LinearLayoutManager(requireContext())
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