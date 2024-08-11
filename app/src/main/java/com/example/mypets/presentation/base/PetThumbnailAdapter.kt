package com.example.mypets.presentation.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mypets.databinding.ItemThumbnailBinding
import com.example.mypets.data.model.Pet

class PetThumbnailAdapter :
    ListAdapter<Pet, PetThumbnailAdapter.ThumbnailViewHolder>(object :
        DiffUtil.ItemCallback<Pet>() {
        override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        runCatching {
            holder.bind(getItem(position))
        }.onFailure { exception ->
            Log.e("PetThumbnailAdapter", "Exception! ${exception.message}")
        }
    }

    class ThumbnailViewHolder(binding: ItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val thumbnailView = binding.thumbnail
        fun bind(item: Pet) {
            thumbnailView.load(item.thumbnail){
                crossfade(true)
            }
        }
    }
}
