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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.NotesViewModel.NoteViewModel
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.background
import com.example.notesapp.ui.theme.button
import com.example.notesapp.ui.theme.listShapeBackground
import com.example.notesapp.ui.theme.text
import kotlinx.coroutines.launch


@Composable
fun InsertNote(viewModel: NoteViewModel = viewModel(), noteID: Int?) {
    val note by viewModel.getNoteById(noteID ?: -1).observeAsState()
    var title by remember(note) { mutableStateOf(note?.title ?: "") }
    var desc by remember(note) { mutableStateOf(note?.desc ?: "") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isBlank() || desc.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Please fill the missing fields!"
                            )
                        }
                    } else {
                        if (note == null) {
                            viewModel.upsert(Note(title = title, desc = desc))
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Note Saved!"
                                )
                            }
                        }
                        else{
                            viewModel.upsert(note!!.copy(title=title, desc = desc))
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Note Updated!"
                                )
                            }
                        }
                    }
                },
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
                text = "Insert Data",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = text
            )
            Spacer(Modifier.height(24.dp))
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title", color = text) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = listShapeBackground,
                    unfocusedContainerColor = listShapeBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = text,
                    unfocusedTextColor = text

                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = desc,
                onValueChange = { desc = it },
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
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = text,
                    unfocusedTextColor = text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(16.dp))

            )
        }
    }
}

