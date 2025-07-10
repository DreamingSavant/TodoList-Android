package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.TodoDatabase
import com.example.todolist.ui.TodoScreen
import com.example.todolist.viewmodel.TodoViewModel
import com.example.todolist.viewmodel.TodoViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get DAO from Room DB
        val dao = TodoDatabase.getDatabase(applicationContext).todoDao()

        // Provide ViewModelFactory with the DAO
        var factory = TodoViewModelFactory(dao)

        setContent {
            // Use ViewModel with the factory
            val viewModel: TodoViewModel = viewModel(factory = factory)
            // Load the UI
            TodoScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
        Greeting("Android")
    }
}