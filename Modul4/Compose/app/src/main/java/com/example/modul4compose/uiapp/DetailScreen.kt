package com.example.modul4compose.uiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modul4compose.data.FriendData

@Composable
fun DetailScreen(friendId: Int, modifier: Modifier = Modifier) {
    // Mencari data teman di FriendData berdasarkan ID yang dikirim
    val friend = FriendData.friends.find { it.id == friendId }

    if (friend != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = friend.photoResId),
                contentDescription = friend.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Teks Judul
            Text(
                text = friend.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )

            //Tanggal / Keterangan Waktu
            Text(
                text = friend.date,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            // Label Deskripsi
            Text(
                text = "Feature:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Isi Deskripsi Lengkap
            Text(
                text = friend.feature,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    } else {
        // if tidak ditemukan
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Data teman tidak ditemukan")
        }
    }
}