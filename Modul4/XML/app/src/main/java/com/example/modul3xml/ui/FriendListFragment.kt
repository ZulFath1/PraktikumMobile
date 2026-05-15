package com.example.modul3xml.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentFriendListBinding
import kotlinx.coroutines.launch

class FriendListFragment : Fragment() {

    private var _binding: FragmentFriendListBinding? = null
    private val binding get() = _binding!!

    // Inisialisasi ViewModel menggunakan Factory
    private val viewModel: FriendViewModel by viewModels {
        FriendViewModelFactory("Fathi")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengamati StateFlow dari ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.friendsState.collect { friendsList ->

                    // Setup Featured Friends (Horizontal) - ambil 5 teman pertama
                    binding.rvFeaturedFriends.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvFeaturedFriends.adapter = FriendAdapter(friendsList.take(5)) { selectedFriend ->

                        viewModel.onFriendClicked(selectedFriend) // Panggil log ViewModel

                        // Navigasi ke Detail Fragment membawa ID
                        val bundle = Bundle().apply { putInt("friendId", selectedFriend.id) }
                        findNavController().navigate(R.id.action_friendListFragment_to_detailFragment, bundle)
                    }

                    // Setup All Friends (Vertical)
                    binding.rvAllFriends.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvAllFriends.adapter = FriendAdapter(friendsList) { selectedFriend ->

                        viewModel.onFriendClicked(selectedFriend) // Panggil log ViewModel

                        // Navigasi ke Detail Fragment membawa ID
                        val bundle = Bundle().apply { putInt("friendId", selectedFriend.id) }
                        findNavController().navigate(R.id.action_friendListFragment_to_detailFragment, bundle)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}