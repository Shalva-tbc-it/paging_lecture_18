package com.example.paging.network

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.base.BaseFragment
import com.example.paging.databinding.FragmentHomeBinding
import com.example.paging.network.adapter.PagingAdapter
import com.example.paging.network.view_model.ApiViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var adapter: PagingAdapter
    private val viewModel: ApiViewModel by viewModels()

    override fun start() {
        setAdapter()
        bind()
    }

    override fun clickListener() {

    }

    private fun setAdapter() {
        adapter = PagingAdapter()
        binding.recyclerViewPaging.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
    }

    private fun bind() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersPagingData.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }
}
