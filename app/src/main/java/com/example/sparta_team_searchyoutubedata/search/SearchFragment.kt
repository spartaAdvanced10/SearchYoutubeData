package com.example.sparta_team_searchyoutubedata.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sparta_team_searchyoutubedata.databinding.FragmentSearchBinding
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
    }
}