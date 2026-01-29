package com.dam.mynoteapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos ViewModel
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val rvTasks = view.findViewById<RecyclerView>(R.id.rvTasks)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        // Configuración del layoutManager
        rvTasks.layoutManager = LinearLayoutManager(context)

        //Configuración del adapter
        adapter = TaskAdapter(viewModel.getAll()) { position ->
            // Navegar al pulsar una tarea existente (para editar)
            val bundle = Bundle().apply { putInt("taskIndex", position) }
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
        rvTasks.adapter = adapter

        setFragmentResultListener("actualizar_lista") { _, bundle ->
            val pos = bundle.getInt("posicionModificada")
            // Solo notificamos cambio si la posición es válida
            if (pos >= 0) {
                adapter.notifyItemChanged(pos)
            }
        }

        btnAdd.setOnClickListener {
            val nuevaTarea = Tarea("Nueva Tarea", "", "", "")

            viewModel.setTarea(nuevaTarea)
            val nuevaPosicion = viewModel.getSize() - 1

            adapter.notifyItemInserted(nuevaPosicion)

            val bundle = Bundle().apply { putInt("taskIndex", nuevaPosicion) }
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }
    }
}