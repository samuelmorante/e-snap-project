package com.dam.mynoteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val lista: MutableList<Tarea>,
    private val onTaskClicked: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTarea: TextView = view.findViewById(R.id.tvTarea)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val item = lista[position]
        holder.tvTarea.text = item.tarea

        //Para detectar el click
        holder.itemView.setOnClickListener {
            onTaskClicked(position)
        }
    }

    override fun getItemCount() = lista.size

}