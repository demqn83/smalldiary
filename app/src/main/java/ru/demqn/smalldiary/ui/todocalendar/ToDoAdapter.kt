package ru.demqn.smalldiary.ui.todocalendar

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.demqn.smalldiary.data.repositories.ToDoEntity
import ru.demqn.smalldiary.databinding.ViewHolderTodosBinding
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter(private val todos: List<ToDoEntity>, private var listener: ToDoViewHolderClicks) :
    RecyclerView.Adapter<ToDoViewHolder>() {

    private fun getItem(position: Int) = todos[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            ViewHolderTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = todos.size
}

class ToDoViewHolder(
    private val binding: ViewHolderTodosBinding,
    private var listener: ToDoViewHolderClicks
) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SimpleDateFormat")
    fun onBind(todo: ToDoEntity) {
        binding.tvNameTodo.text = todo.name
        binding.tvTimeTodo.text = SimpleDateFormat("HH:mm").format(Date(todo.date_start))
        Log.d("logerr", todo.name)

        binding.root.setOnClickListener {
            listener.clickOpenToDoToDoDescription(todo)
        }
    }
}

interface ToDoViewHolderClicks {
    fun clickOpenToDoToDoDescription(todo: ToDoEntity)
}