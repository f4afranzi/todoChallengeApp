package com.example.todochallengeapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todochallengeapp.R
import com.example.todochallengeapp.data.Task
import java.util.UUID

class TodoListAdapter(private val onClickListener: (id: String) -> Unit): RecyclerView
.Adapter<TodoListAdapter.TodoListViewHolder>() {
    private var tasks: List<Task> = listOf()

    class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val todoItemVewText = itemView.findViewById<TextView>(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val task = tasks[position]
        holder.todoItemVewText.setText(task.text)
        holder.itemView.setOnClickListener{
            onClickListener(task.id)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun submitItems(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }
}