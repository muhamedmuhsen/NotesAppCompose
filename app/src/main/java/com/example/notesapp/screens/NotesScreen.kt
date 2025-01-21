package com.example.notesapp.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.NotesViewModel.NoteViewModel
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.background
import com.example.notesapp.ui.theme.button
import com.example.notesapp.ui.theme.listShapeBackground
import com.example.notesapp.ui.theme.text

@Preview(showSystemUi = true)
@Composable
 fun NotesScreen(viewModel: NoteViewModel = viewModel()) {
    val notes by viewModel.getAllNotes().observeAsState(initial = emptyList())


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                contentColor = Color.White,
                containerColor = button,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
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
                text = "Create Notes \nCrud",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = text
            )
            LazyColumn {
                items(notes) {note->
                    ListShape(note, onDelete = {}, onUpdate = {})
                }
            }
        }
    }
}

@Composable
private fun ListShape(note: Note, onUpdate: (Note) -> Unit, onDelete: (Note) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(listShapeBackground)
    ) {
        Column(
            Modifier
                .padding(16.dp)
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = text
            )
            Text(
                text = note.desc,
                fontSize = 16.sp,
                color = text
            )
        }
        NoteMenu(
            onUpdate = { onUpdate(note) },
            onDelete = { onDelete(note) },
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    NotesScreen(viewModel = FakeViewModel())
}

// Fake ViewModel for Preview
class FakeViewModel : NoteViewModel(Application()) {
    override fun getAllNotes() = MutableLiveData(listOf(
        Note(1, "Sample Note 1", "Description 1"),
        Note(2, "Sample Note 2", "Description 2")
    ))
}


@Composable
private fun NoteMenu(
    modifier: Modifier = Modifier,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        // Icon that triggers the menu
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More options",
            tint = Color.White,
            modifier = Modifier
                .padding(12.dp)
                .clickable { expanded = !expanded }
        )

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(clippingEnabled = true),
            // Adjust these offset values as needed
            offset = DpOffset(x = (-32).dp, y = (-8).dp),
            modifier = Modifier
                .background(Color.White)
                .clip(RoundedCornerShape(32.dp))
        ) {
            DropdownMenuItem(
                text = { Text(text = "Update", color = Color.Black) },
                onClick = {
                    expanded = false
                    onUpdate()
                }
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.5.dp,
                color = Color.LightGray
            )

            DropdownMenuItem(
                text = { Text(text = "Delete", color = Color.Black) },
                onClick = {
                    expanded = false
                    onDelete()
                }
            )
        }
    }
}