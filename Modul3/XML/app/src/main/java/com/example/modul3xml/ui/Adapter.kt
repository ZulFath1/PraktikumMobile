package com.example.modul3xml.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modul3xml.databinding.ItemFriendBinding
import com.example.modul3xml.model.Friend

class FriendAdapter(
    private val friends: List<Friend>,
    private val onDetailClick: (Int) -> Unit
) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    // ViewHolder: Kelas yang bertugas memegang dan mengelola 1 kotak item_friend.xml
    inner class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend) {
            // Memasukkan teks ke dalam id yang kamu buat di XML tadi
            binding.tvName.text = friend.name
            binding.tvDate.text = friend.date
            binding.tvFeature.text = "Feature: ${friend.feature}"

            // Memasukkan gambar menggunakan library Glide agar tidak lag
            Glide.with(itemView.context)
                .load(friend.photoResId)
                .into(binding.ivPhoto)

            // Mengatur tombol Instagram (Intent Eksplisit)
            binding.btnInstagram.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(friend.instagramUrl))
                itemView.context.startActivity(intent)
            }

            // Mengatur tombol Detail (Memicu navigasi dengan membawa ID)
            binding.btnDetail.setOnClickListener {
                onDetailClick(friend.id)
            }
        }
    }

    // Dipanggil saat RecyclerView butuh mencetak kotak kosong baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    // Memberitahu RecyclerView berapa banyak total data temanmu
    override fun getItemCount(): Int = friends.size

    // Mengisi kotak kosong tersebut dengan data berdasarkan urutannya
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friends[position])
    }
}