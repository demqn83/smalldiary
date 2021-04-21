package ru.demqn.smalldiary.ui.todocalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.demqn.smalldiary.data.repositories.SmallDiaryRepository
import ru.demqn.smalldiary.data.repositories.ToDoEntity

class ToDoCalendarFragmentViewModel(private val smallDiaryRepository: SmallDiaryRepository) :
    ViewModel() {

    private var _todoList: MutableLiveData<Pair<List<ToDoEntity>, Long>> = MutableLiveData()
    val todoList: LiveData<Pair<List<ToDoEntity>, Long>> get() = _todoList

    fun getListToDo(date: Long) {
        _todoList.value = Pair(smallDiaryRepository.getListToDo(date),date)
    }
}

