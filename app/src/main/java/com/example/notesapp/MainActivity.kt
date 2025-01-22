package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notesapp.screens.InsertNote
import com.example.notesapp.screens.NotesScreen
import com.example.notesapp.ui.theme.NotesAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val padding = innerPadding
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NotesScreen
                    ) {
                        composable<NotesScreen> {
                            NotesScreen(navController = navController)
                        }
                        composable<InsertNoteScreen> {
                            InsertNote(
                                noteID = null
                            )
                        }
                        composable<UpdateNoteScreen> {
                            val args = it.toRoute<UpdateNoteScreen>()
                            InsertNote(noteID = args.noteID)
                        }
                    }

                }
            }
        }
    }
}

@Serializable
object NotesScreen

@Serializable
object InsertNoteScreen

@Serializable
data class UpdateNoteScreen(val noteID: Int)