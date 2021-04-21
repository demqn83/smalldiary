package ru.demqn.smalldiary.data.repositories

import ru.demqn.smalldiary.data.db.ToDoJson

class SmallDiaryRepository {
    fun getListToDo(startDate: Long): List<ToDoEntity> {
        val endDay = startDate + 86400000
        return ToDoJson().todoEntity.filter { it.date_start in startDate until endDay }
    }
}