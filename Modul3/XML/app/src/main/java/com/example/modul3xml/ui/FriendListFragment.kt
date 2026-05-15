package com.example.modul3xml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.data.FriendData
import com.example.modul3xml.databinding.FragmentFriendListBinding

class FriendListFragment : Fragment() {

    // Setup ViewBinding khusus untuk Fragment
    private var _binding: FragmentFriendListBinding? = null
    private val binding get() = _binding!!

    // Fungsi ini bertugas "mencetak" tampilan fragment_friend_list.xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Fungsi ini bertugas mengisi data setelah tampilannya selesai dicetak
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil gudang data temanmu
        val friends = FriendData.friends


        val featuredAdapter = FriendAdapter(friends.take(5)) { friendId ->
            navigateToDetail(friendId)
        }
        binding.rvFeaturedFriends.adapter = featuredAdapter

        // 2. Setup Rak Bawah (All - Ambil semua teman)
        val allAdapter = FriendAdapter(friends) { friendId ->
            navigateToDetail(friendId)
        }
        binding.rvAllFriends.adapter = allAdapter
    }

    // Fungsi untuk berpindah ke layar Detail membawa ID
    private fun navigateToDetail(friendId: Int) {
        // Bundle adalah "kotak paket" untuk membawa data antar layar di XML
        val bundle = Bundle().apply {
            putInt("friendId", friendId)
        }
        // Menyuruh sistem navigasi bergerak ke rute Detail membawa kotak paket
        findNavController().navigate(R.id.action_friendListFragment_to_detailFragment, bundle)
    }

    // Wajib di Fragment: Menghapus binding saat layar ditutup agar tidak membebani memori
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}