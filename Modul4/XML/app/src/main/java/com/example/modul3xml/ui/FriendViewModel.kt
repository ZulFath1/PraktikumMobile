package com.example.modul3xml.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul3xml.data.FriendData
import com.example.modul3xml.model.Friend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class FriendViewModel(private val userName: String) : ViewModel() {

    // StateFlow untuk daftar teman
    private val _friendsState = MutableStateFlow<List<Friend>>(emptyList())
    val friendsState: StateFlow<List<Friend>> = _friendsState.asStateFlow()

    init {
        loadFriends()
        Timber.d("ViewModel diinisialisasi oleh: $userName") // Syarat Log
    }

    private fun loadFriends() {
        val data = FriendData.friends
        _friendsState.value = data
        Timber.d("Data item masuk ke dalam list: ${data.size} kawan") // Syarat Log
    }

    // Fungsi log saat tombol ditekan
    fun onFriendClicked(friend: Friend) {
        Timber.d("Button Detail ditekan untuk: ${friend.name}") // Syarat Log
        Timber.d("Berpindah ke halaman Detail dengan data: $friend") // Syarat Log
    }
}

class FriendViewModelFactory(private val name: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FriendViewModel(name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
