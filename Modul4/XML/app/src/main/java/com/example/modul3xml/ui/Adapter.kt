package com.example.modul3xml.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modul3xml.databinding.ItemFriendBinding
import com.example.modul3xml.model.Friend
import timber.log.Timber

class FriendAdapter(
    private val friends: List<Friend>,
    private val onDetailClick: (Friend) -> Unit // Ubah tipe menjadi Friend
) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend) {
            binding.tvName.text = friend.name
            binding.tvDate.text = friend.date
            binding.tvFeature.text = "Feature: ${friend.feature}"

            Glide.with(itemView.context)
                .load(friend.photoResId)
                .into(binding.ivPhoto)

            // Mengatur tombol Instagram dengan TIMBER LOG
            binding.btnInstagram.setOnClickListener {
                Timber.d("Tombol Explicit Intent (Instagram) ditekan. Membuka URL: ${friend.instagramUrl}")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(friend.instagramUrl))
                itemView.context.startActivity(intent)
            }

            // Memicu navigasi dengan membawa objek friend
            binding.btnDetail.setOnClickListener {
                onDetailClick(friend)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friends[position])
    }
}