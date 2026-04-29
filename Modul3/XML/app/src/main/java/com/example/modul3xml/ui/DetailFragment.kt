package com.example.modul3xml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.modul3xml.data.FriendData
import com.example.modul3xml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Menangkap ID teman yang dikirim dari layar list
        val friendId = arguments?.getInt("friendId") ?: 0

        // 2. Mencari data teman di FriendData berdasarkan ID tersebut
        val friend = FriendData.friends.find { it.id == friendId }

        if (friend != null) {
            // 3. Jika ketemu masukkan datanya ke komponen visual XML
            binding.tvDetailName.text = friend.name
            binding.tvDetailDate.text = friend.date
            binding.tvDetailFeature.text = friend.feature

            // 4. Memuat gambar besar menggunakan Glide
            Glide.with(this)
                .load(friend.photoResId)
                .into(binding.ivDetailPhoto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}