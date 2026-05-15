package com.example.modul4compose.uiapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modul4compose.model.Friend

@Composable
fun FriendListScreen(
    friends: List<Friend>,
    onNavigateToDetail: (Friend) -> Unit,
    onInstagramClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            Text(
                text = "Featured Friends",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(friends) { friend ->
                    Box (modifier = Modifier.width(350.dp)) {
                        FriendItem(
                            friend = friend,
                            onDetailClick = { onNavigateToDetail(friend) },
                            onInstagramClick = { onInstagramClick(friend.instagramUrl) }
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "All Friends",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
            )
        }

        items(friends) { friend ->
            FriendItem(
                friend = friend,
                onDetailClick = { onNavigateToDetail(friend) },
                onInstagramClick = { onInstagramClick(friend.instagramUrl) }
            )
        }
    }
}