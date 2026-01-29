package com.dam.mynoteapp // <--- Pon tu paquete

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val viewModel: TaskViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt("taskIndex") ?: -1

        val etTask = view.findViewById<EditText>(R.id.etTarea)
        val etCreation = view.findViewById<EditText>(R.id.etDataInicio)
        val etEnd = view.findViewById<EditText>(R.id.etDataFin)
        val etDetails = view.findViewById<EditText>(R.id.etDetalles)
        val btnAction = view.findViewById<Button>(R.id.btAdd)

        if (index != -1) {
            val tareaActual = viewModel.getTarea(index)
            etTask.setText(tareaActual.tarea)
            etCreation.setText(tareaActual.dataCreacion)
            etEnd.setText(tareaActual.dataFin)
            etDetails.setText(tareaActual.detalles)
            btnAction.text = "Guardar"
        } else {
            btnAction.text = "Crear"
        }

        btnAction.setOnClickListener {
            if (etTask.text.isEmpty() || etCreation.text.isEmpty()) {
                Toast.makeText(context, "Falta nombre o fecha", Toast.LENGTH_SHORT).show()
            } else {
                val tareaGuardada = Tarea(
                    etTask.text.toString(),
                    etCreation.text.toString(),
                    etEnd.text.toString(),
                    etDetails.text.toString()
                )

                viewModel.updateTarea(index, tareaGuardada)

                // Avisar al FirstFragment
                val result = Bundle()
                result.putInt("posicionModificada", index)
                setFragmentResult("actualizar_lista", result)

                findNavController().popBackStack()
            }
        }
    }
}