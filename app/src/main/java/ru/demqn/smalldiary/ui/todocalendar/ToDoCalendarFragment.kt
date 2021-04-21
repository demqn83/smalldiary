package ru.demqn.smalldiary.ui.todocalendar

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.demqn.smalldiary.R
import ru.demqn.smalldiary.data.repositories.SmallDiaryRepository
import ru.demqn.smalldiary.data.repositories.ToDoEntity
import ru.demqn.smalldiary.databinding.FragmentToDoCalendarBinding
import java.util.*


class ToDoCalendarFragment : Fragment(R.layout.fragment_to_do_calendar) {

    private val toDoCalendarFragmentViewModel: ToDoCalendarFragmentViewModel by viewModels {
        ToDoCalendarFragmentViewModelFactory(SmallDiaryRepository())
    }
    private lateinit var adapterList: HoursAdapter
    private var todos: List<ToDoEntity> = listOf()

    private lateinit var binding: FragmentToDoCalendarBinding
    private var listener: ToDoCalendarFragmentClicks? = null


    private val listenerToDoAdapter = object : ToDoViewHolderClicks {
        override fun clickOpenToDoToDoDescription(todo: ToDoEntity) {
            listener?.openToDoToDoDescription(todo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentToDoCalendarBinding.bind(view)
        initViews()
        initObserves()
        val startDay = calendarToTimeInMillis(binding.calendarView.selectedDates[0])
        loadData(startDay)
    }

    private fun initViews() {
        val startDay = calendarToTimeInMillis(binding.calendarView.selectedDates[0])
        adapterList = HoursAdapter(todos, startDay, listenerToDoAdapter)
        binding.listTodoView.adapter = adapterList
        binding.listTodoView.layoutManager = LinearLayoutManager(requireContext())

        binding.calendarView.setOnDayClickListener { eventDay ->
            val mstartDay = calendarToTimeInMillis(eventDay.calendar)
            loadData(mstartDay)
            Log.d("logerr", mstartDay.toString())
        }
    }

    private fun initObserves() {
        toDoCalendarFragmentViewModel.todoList.observe(viewLifecycleOwner, ::updateListToDo)
    }

    private fun updateListToDo(shuffledList: Pair<List<ToDoEntity>, Long>) {
        adapterList.bindMovies(shuffledList.first, shuffledList.second)
        todos = shuffledList.first
    }

    private fun loadData(date: Long) {
        toDoCalendarFragmentViewModel.getListToDo(date)
    }

    private fun calendarToTimeInMillis(calendar: Calendar): Long {
        return GregorianCalendar(
            (calendar.get(Calendar.YEAR)),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).timeInMillis
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToDoCalendarFragmentClicks) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToDoCalendarFragment()
    }

    interface ToDoCalendarFragmentClicks {
        fun openToDoToDoDescription(todo: ToDoEntity)
    }
}