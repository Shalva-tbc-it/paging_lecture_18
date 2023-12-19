package com.example.paging.network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.databinding.PagingItemBinding
import com.example.paging.network.model.User

class PagingAdapter() :
    PagingDataAdapter<User, PagingAdapter.PagingViewHolder>(diffCallback = UserListDiffCallback()) {
    class PagingViewHolder(private val binding: PagingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) = with(binding) {
            tvFirstname.text = item.firstName
            tvLastname.text = item.lastName
            tvEmail.text = item.email
        }
    }

    class UserListDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val item: User? = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            PagingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}