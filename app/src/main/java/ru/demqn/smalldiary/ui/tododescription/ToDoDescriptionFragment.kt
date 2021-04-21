package ru.demqn.smalldiary.ui.tododescription

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import ru.demqn.smalldiary.R
import ru.demqn.smalldiary.data.repositories.ToDoEntity
import ru.demqn.smalldiary.databinding.FragmentToDoDescriptionBinding
import java.text.SimpleDateFormat
import java.util.*

class ToDoDescriptionFragment : Fragment(R.layout.fragment_to_do_description) {

    private lateinit var binding: FragmentToDoDescriptionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentToDoDescriptionBinding.bind(view)
        loadData()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadData() {
        val todo: ToDoEntity = Json.decodeFromString(requireNotNull(arguments?.getString(ID_KEY)))

        with(binding){
            tvName.text = todo.name
            tvDate.text = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date(todo.date_start))
            tvDescription.text = todo.description
        }
    }

    companion object {
        private const val ID_KEY = "todo_entity"

        @JvmStatic
        fun newInstance(todo: ToDoEntity) = ToDoDescriptionFragment().apply {
            arguments = Bundle().apply {
                putString(ID_KEY, Json.encodeToString(todo))
            }
        }
    }


}