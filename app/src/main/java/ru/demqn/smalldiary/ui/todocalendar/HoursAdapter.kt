package ru.demqn.smalldiary.ui.todocalendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.demqn.smalldiary.R
import ru.demqn.smalldiary.data.repositories.ToDoEntity
import ru.demqn.smalldiary.databinding.ViewHolderHoursBinding
import ru.demqn.smalldiary.utils.Setting

class HoursAdapter(
    private var todos: List<ToDoEntity>,
    private var date: Long,
    private val listener: ToDoViewHolderClicks
) : RecyclerView.Adapter<HoursViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder(
            ViewHolderHoursBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val dateFilterStart = date + position * 60 * 60 * 1000
        val dateFilterEnd = dateFilterStart + (59 * 60 * 1000)
        holder.onBind(position, todos.filter { it.date_start in dateFilterStart until dateFilterEnd })
    }

    override fun getItemCount(): Int = Setting.countHours

    fun bindMovies(newTodos: List<ToDoEntity>, newDate: Long) {
        todos = newTodos
        date = newDate
        notifyDataSetChanged()
    }

}

class HoursViewHolder(
    private val binding: ViewHolderHoursBinding,
    private var listener: ToDoViewHolderClicks
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(hour: Int, todos: List<ToDoEntity>) {
        binding.vhChour.text = binding.root.resources.getString(R.string.hour_start_end, hour, hour)
        binding.listTodosView.adapter = ToDoAdapter(todos, listener)
        binding.listTodosView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}
