package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PesoAeronaveFragment : Fragment(R.layout.peso_aeronave_fragment) {

    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    private fun nextFragment() {
        listener?.onFragmentInteraction(LimitePesoFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(CombustibleFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.peso_aeronave_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 5)
        editor.apply()

        // Restaurar el valor guardado
        val trip = sharedPreferences.getString("trip", "0")
        view.findViewById<EditText>(R.id.editText_trip).setText(trip)
        val aeronave = sharedPreferences.getString("aeronave", "0")
        view.findViewById<EditText>(R.id.editText_aeronave).setText(aeronave)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_next)
        fab.setOnClickListener {
            saveData()
            nextFragment()
        }

        val fab1: FloatingActionButton = view.findViewById(R.id.fab_previous)
        fab1.setOnClickListener {
            saveData()
            previousFragment()
        }

        val fab2: FloatingActionButton = view.findViewById(R.id.fab_clear)
        fab2.setOnClickListener{
            view.findViewById<EditText>(R.id.editText_trip).setText("0")
            view.findViewById<EditText>(R.id.editText_aeronave).setText("0")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val trip = requireView().findViewById<EditText>(R.id.editText_trip).text.toString()
        editor.putString("trip", trip)
        val aeronave = requireView().findViewById<EditText>(R.id.editText_aeronave).text.toString()
        editor.putString("aeronave", aeronave)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}