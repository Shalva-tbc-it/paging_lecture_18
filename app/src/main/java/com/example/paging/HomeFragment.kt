package com.example.paging

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging.base.BaseFragment
import com.example.paging.databinding.FragmentHomeBinding
import com.example.paging.network.adapter.PagingAdapter
import com.example.paging.network.view_model.ApiViewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var pagingAdapter: PagingAdapter
    private val viewModel: ApiViewModel by viewModels()

    override fun start() {
        setAdapter()
        bind()
    }

    private fun setAdapter() {
        pagingAdapter = PagingAdapter()
        binding.recyclerViewPaging.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pagingAdapter
        }

    }

    private fun bind() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userData.collect { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}
