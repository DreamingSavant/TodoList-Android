package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.ToDo
import com.example.todolist.data.TodoDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val dao: TodoDao) : ViewModel() {

    val todos: StateFlow<List<ToDo>> = dao.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(task: String) {
        viewModelScope.launch {
            dao.insertTodo(ToDo(task = task))
        }
    }

    fun deleteTodo(todo: ToDo) {
        viewModelScope.launch {
            dao.deleteTodo(todo)
        }
    }
}