package com.example.todochallengeapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todochallengeapp.R
import com.example.todochallengeapp.data.Task
import com.example.todochallengeapp.data.TodoRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoRepository = TodoRepository()
        val todoList = view.findViewById<RecyclerView>(R.id.todoList)
        val adapter = TodoListAdapter({id: String ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("New Dialog")
                .setView(R.layout.item_dialog_text_view)
                .setNegativeButton("Cancel") { _, _ -> }
                .setPositiveButton("Save") { dialog, which ->
                    val alertDialog = dialog as AlertDialog
                    val text = alertDialog.findViewById<EditText>(R.id.newTodoText)!!.text.toString()
                    todoRepository.updateTask(id, text).enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            //fetchTasks(todoRepository, adapter)
                        }
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }
                .show()
        })
        todoList.layoutManager = LinearLayoutManager(requireContext())
        todoList.adapter = adapter
        fetchTasks(todoRepository, adapter)

        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            //val dialogView = View.inflate(requireContext(), R.layout.item_dialog_text_view, view as ViewGroup)
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("New Dialog")
                .setView(R.layout.item_dialog_text_view)
                .setNegativeButton("Cancel") { _, _ -> }
                .setPositiveButton("Save") { dialog, which ->
                    val alertDialog = dialog as AlertDialog
                    val text = alertDialog.findViewById<EditText>(R.id.newTodoText)!!.text.toString()
                    todoRepository.addTask(text).enqueue(object : Callback<Task> {
                        override fun onResponse(call: Call<Task>, response: Response<Task>) {
                            fetchTasks(todoRepository, adapter)
                        }
                        override fun onFailure(call: Call<Task>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                }
                .show()
        }
    }

    private fun fetchTasks(
        todoRepository: TodoRepository,
        adapter: TodoListAdapter
    ) {
        todoRepository.getTasks().enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                val tasks = response.body()
                if (tasks != null) {
                    adapter.submitItems(tasks)
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}