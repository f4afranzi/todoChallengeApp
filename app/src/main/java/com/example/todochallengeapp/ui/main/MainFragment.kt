package com.example.todochallengeapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todochallengeapp.R
import com.example.todochallengeapp.data.Task
import com.example.todochallengeapp.data.TodoRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
        val todoList = view.findViewById<RecyclerView>(R.id.todoList)
        val adapter = TodoListAdapter()
        todoList.layoutManager = LinearLayoutManager(requireContext())
        todoList.adapter = adapter
        val todoRepository = TodoRepository()
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

        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            TODO("create Dialog with Text view and save button")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}