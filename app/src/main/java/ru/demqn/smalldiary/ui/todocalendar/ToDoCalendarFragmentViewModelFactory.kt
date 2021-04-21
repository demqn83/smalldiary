package ru.demqn.smalldiary.ui.todocalendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.demqn.smalldiary.data.repositories.SmallDiaryRepository

class ToDoCalendarFragmentViewModelFactory(private val smallDiaryRepository: SmallDiaryRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ToDoCalendarFragmentViewModel::class.java -> ToDoCalendarFragmentViewModel(
            smallDiaryRepository
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}