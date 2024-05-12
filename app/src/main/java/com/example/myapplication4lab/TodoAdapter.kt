package com.example.myapplication4lab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication4lab.database.Todo
import com.example.myapplication4lab.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoAdapter(items:List<Todo> , repository: TodoRepository,
                  viewModel: MainActivityData):Adapter<TodoViewHolder>() {

    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_memo,parent,false)
        context = parent.context
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.checkBox2.text = items[position].item

        val currentItem = items[position]
        holder.checkBox2.text = currentItem.item

        holder.checkBox2.setOnLongClickListener {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("Update Todo Item")
            val input = EditText(context)
            input.setText(currentItem.item)
            builder.setView(input)

            builder.setPositiveButton("Update") { dialog, which ->
                val updatedItem = input.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    repository.update(currentItem.id ?: 0, updatedItem) // Assuming 0 as default value if id is null
                    val data = repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

            builder.show()
            true // Return true to indicate that the long click event is consumed
        }

        holder.ivEdit.setOnClickListener {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("Update Todo Item")
            val input = EditText(context)
            input.setText(currentItem.item)
            builder.setView(input)

            builder.setPositiveButton("Update") { dialog, which ->
                val updatedItem = input.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    repository.update(currentItem.id ?: 0, updatedItem) // Assuming 0 as default value if id is null
                    val data = repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                        Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

            builder.show()
        }
        holder.ivDelete.setOnClickListener {
            val isChecked = holder.checkBox2.isChecked

            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items[position])
                    val data = repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
            } else {
                Toast.makeText(context, "Select the item to be deleted", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }
}