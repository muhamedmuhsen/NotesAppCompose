package com.example.notesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.background
import com.example.notesapp.ui.theme.button
import com.example.notesapp.ui.theme.listShapeBackground
import com.example.notesapp.ui.theme.text

@Preview(showSystemUi = true)
@Composable
private fun InsertNote() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                contentColor = Color.White,
                containerColor = button,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null
                )
            }
        }
    ) { innerpadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(background)
                .padding(innerpadding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Insert Data ",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = text
            )
            Spacer(Modifier.height(24.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Title", color = text) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = listShapeBackground,
                    unfocusedContainerColor = listShapeBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Enter description",
                        color = text
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = listShapeBackground,
                    unfocusedContainerColor = listShapeBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(16.dp))

            )
        }
    }
}