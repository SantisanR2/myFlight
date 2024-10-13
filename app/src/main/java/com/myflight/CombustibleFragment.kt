package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CombustibleFragment : Fragment(R.layout.combustible_fragment) {

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
        listener?.onFragmentInteraction(PesoAeronaveFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(AeropuertoAterrizajeFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.combustible_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 4)
        editor.apply()

        // Restaurar el valor guardado
        val cad = sharedPreferences.getString("CAD", "0")
        view.findViewById<EditText>(R.id.editText_cad).setText(cad)
        val cs = sharedPreferences.getString("CS", "0")
        view.findViewById<EditText>(R.id.editText_cs).setText(cs)

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
            view.findViewById<EditText>(R.id.editText_cad).setText("0")
            view.findViewById<EditText>(R.id.editText_cs).setText("0")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val cad = requireView().findViewById<EditText>(R.id.editText_cad).text.toString()
        editor.putString("CAD", cad)
        val cs = requireView().findViewById<EditText>(R.id.editText_cs).text.toString()
        editor.putString("CS", cs)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}