package com.dam.mynoteapp

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    private val listaTareas = mutableListOf<Tarea>()
    // Añadimos tareas
    init {
        listaTareas.add(Tarea("Estudiar Android", "22/01", "25/01", "Repasar RecyclerView"))
        listaTareas.add(Tarea("Hacer la compra", "23/01", "23/01", "Leche y Pan"))
        listaTareas.add(Tarea("Gimnasio", "24/01", "24/01", "Día de pierna"))
    }

    fun getAll(): MutableList<Tarea> {
        return listaTareas
    }

    fun setTarea(t: Tarea){
        listaTareas.add(t)
    }

    fun getSize(): Int {
        return listaTareas.size
    }

    fun getTarea(index: Int): Tarea {
        return listaTareas[index]
    }

    fun updateTarea(index: Int, tareaEditada: Tarea){
        listaTareas[index] = tareaEditada
    }
}
