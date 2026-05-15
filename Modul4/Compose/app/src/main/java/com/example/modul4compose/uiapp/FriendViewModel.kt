package com.example.modul4compose.uiapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul4compose.data.FriendData
import com.example.modul4compose.model.Friend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class FriendViewModel(private val userName: String) : ViewModel() {

    // StateFlow untuk daftar kawan
    private val _friendsState = MutableStateFlow<List<Friend>>(emptyList())
    val friendsState: StateFlow<List<Friend>> = _friendsState.asStateFlow()

    init {
        loadFriends()
        Timber.d("ViewModel diinisialisasi oleh: $userName")
    }

    private fun loadFriends() {
        val data = FriendData.friends
        _friendsState.value = data
        Timber.d("Data item masuk ke dalam list: ${data.size} friend")
    }

    // Fungsi untuk log ketika button ditekan
    fun onFriendClicked(friend: Friend) {
        Timber.d("Button Detail ditekan untuk: ${friend.name}")
        Timber.d("Berpindah ke halaman Detail dengan data: $friend")
    }
}

// ViewModel Factory  parameter String
class FriendViewModelFactory(private val name: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FriendViewModel(name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}