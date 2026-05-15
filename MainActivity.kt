package com.example.tugasavatar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {

    // Menyimpan status lifecycle agar UI otomatis ter-update saat status berubah
    private var lifecycleStatus = mutableStateOf("onCreate")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLifecycle("onCreate")

        setContent {
            // THEME: Pengganti themes.xml, mengatur warna global aplikasi
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF1976D2), // Warna Utama
                    secondary = Color(0xFF03DAC5) // Warna Sekunder
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil ProfilScreen dan beri perintah Intent saat tombol ditekan
                    ProfilScreen(
                        status = lifecycleStatus.value,
                        onNavigate = {
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    // --- ACTIVITY LIFECYCLE ---
    override fun onStart() {
        super.onStart()
        updateLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        updateLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        updateLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        updateLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        updateLifecycle("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        updateLifecycle("onRestart")
    }

    // Fungsi untuk mencetak ke Logcat dan memperbarui teks di layar
    private fun updateLifecycle(status: String) {
        // 1. Mencetak ke Logcat
        Log.d("LIFECYCLE_TEST", "Method dipanggil: $status")

        // 2. Perubahan teks pada layar
        lifecycleStatus.value = status

        // 3. Menampilkan pesan pop-up (Toast) di layar
        Toast.makeText(this, "Lifecycle: $status", Toast.LENGTH_SHORT).show()
    }
}

// --- KOMPONEN LAYAR PROFIL ---
@Composable
fun ProfilScreen(status: String, onNavigate: () -> Unit) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState), // <-- Tambahkan baris ini
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
     {
        // GRAPHIC 2: Shape Drawable (Bentuk Lingkaran untuk menampung Foto)
        Image(
            painter = painterResource(id = R.drawable.julpeti),
            contentDescription = "Foto Profil Teman",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // STYLE 1: Style Judul
        Text(
            text = "Nama: Muhammad Dzul Fathi Ahyan",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // STYLE 2: Style Deskripsi
        Text(
            text = "NIM: 241081210011\nProdi: Teknologi Informasi",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // GRAPHIC 3 & STYLE: Card Lifecycle
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Status Lifecycle Terakhir:", fontWeight = FontWeight.Bold)
                Text(
                    text = status,
                    fontSize = 20.sp,
                    color = Color(0xFFE65100),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Card Deskripsi Singkat
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Deskripsi Singkat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Julpeti adalah seorang mahasiswa yang baru saja belajar aplikasi mobile",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // STYLE 3: Style Tombol
        Button(
            onClick = onNavigate,
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Buka Detail Mahasiswa", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true, name = "1. Halaman Profil")
@Composable
fun ProfilScreenPreview() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF1976D2),
            secondary = Color(0xFF03DAC5)
        )
    ) {
        ProfilScreen(status = "onResume", onNavigate = {})
    }
}