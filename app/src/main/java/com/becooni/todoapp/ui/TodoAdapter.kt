package com.becooni.todoapp.ui

import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.becooni.todoapp.R
import com.becooni.todoapp.model.Todo
import com.becooni.todoapp.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.ViewHolder>(Companion) {

    var viewModel: MainViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemTodoBinding.bind(itemView)

        fun bind(item: Todo) {
            binding.item = item
            binding.vm = viewModel

            binding.textView.paintFlags =
                if (item.completed) {
                    binding.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    0
                }

            binding.textView.setOnClickListener {
                it.isVisible = false
                binding.editText.isVisible = true
                binding.editText.requestFocus()
            }

            binding.editText.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel?.onEditTodo(item, v.text)

                    v.isVisible = false
                    binding.textView.isVisible = true

                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    v.clearFocus()
                    false
                } else {
                    true
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem == newItem
    }
}