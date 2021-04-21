package ru.demqn.smalldiary.data.repositories

import kotlinx.serialization.Serializable

@Serializable
data class ToDoEntity(
    val id: Int,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    val description: String
)

@Serializable
data class ListToDoEntity(
    val list:List<ToDoEntity>
)