package com.example.todochallengeapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todochallengeapp.R

class TodoListAdapter: RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val todoItemVewText = itemView.findViewById<TextView>(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.todoItemVewText.setText("Position = $position")
    }

    override fun getItemCount(): Int {
        return 20
    }
}