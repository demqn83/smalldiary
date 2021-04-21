package ru.demqn.smalldiary.ui.todocalendar

import androidx.recyclerview.widget.DiffUtil
import ru.demqn.smalldiary.data.repositories.ToDoEntity

class TodosDiffUtilCallback(
    private val oldList: List<ToDoEntity>,
    private val newList: List<ToDoEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}