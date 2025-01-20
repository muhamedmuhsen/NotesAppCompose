import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.background
import com.example.notesapp.ui.theme.button
import com.example.notesapp.ui.theme.listShapeBackground
import com.example.notesapp.ui.theme.text

@Preview(showSystemUi = true)
@Composable
private fun NotesScreen() {
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
                items(5) {
                    ListShape(Note("title", "this is desc"))
                }
            }


        }

    }
}

@Composable
private fun ListShape(note: Note) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(listShapeBackground)
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        )
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                text = "Title",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = text
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ",
                fontSize = 16.sp,
                color = text
            )
        }
    }
}

@Preview
@Composable
private fun ListShapePreview() {
    ListShape(
        note = Note("Title", "This is Desc!")
    )
}