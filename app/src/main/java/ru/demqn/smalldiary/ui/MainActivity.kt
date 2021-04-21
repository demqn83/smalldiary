package ru.demqn.smalldiary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.demqn.smalldiary.R
import ru.demqn.smalldiary.data.repositories.ToDoEntity
import ru.demqn.smalldiary.ui.todocalendar.ToDoCalendarFragment
import ru.demqn.smalldiary.ui.tododescription.ToDoDescriptionFragment

class MainActivity : AppCompatActivity(), ToDoCalendarFragment.ToDoCalendarFragmentClicks {

    private lateinit var todoCalendarFragment: ToDoCalendarFragment
    private lateinit var toDoDescriptionFragment: ToDoDescriptionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            todoCalendarFragment = ToDoCalendarFragment.newInstance()
            todoCalendarFragment.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container_view, this, FRAGMENT_TODO_CALENDAR)
                    .commit()
            }
        } else {
            todoCalendarFragment =
                supportFragmentManager.findFragmentByTag(FRAGMENT_TODO_CALENDAR) as ToDoCalendarFragment
        }
    }

    override fun openToDoToDoDescription(todo: ToDoEntity) {
        toDoDescriptionFragment = ToDoDescriptionFragment.newInstance(todo)
        toDoDescriptionFragment.apply {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_view, this, FRAGMENT_TODO_DESCRIPTION)
                .commit()
        }
    }

    companion object {
        const val FRAGMENT_TODO_CALENDAR = "ToDoCalendarFragment"
        const val FRAGMENT_TODO_DESCRIPTION = "ToDoDescriptionFragment"
    }
}