package com.becooni.todoapp.extentions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.becooni.todoapp.ui.MainViewModel
import com.becooni.todoapp.model.Todo
import com.becooni.todoapp.ui.TodoAdapter

object RecyclerViewExt {

    @JvmStatic
    @BindingAdapter("setTodoAdapter", "setViewModel")
    fun RecyclerView.setTodoAdapter(items: List<Todo>?, viewModel: MainViewModel?) {
        val adapter = adapter as? TodoAdapter ?: TodoAdapter().also {
            adapter = it
            it.viewModel = viewModel
        }
        adapter.submitList(items)
    }
}