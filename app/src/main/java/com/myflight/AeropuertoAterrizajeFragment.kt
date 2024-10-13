package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AeropuertoAterrizajeFragment : Fragment(R.layout.aeropuerto_aterrizaje_fragment) {

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
        listener?.onFragmentInteraction(CombustibleFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(AeropuertoDespegueFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.aeropuerto_aterrizaje_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 3)
        editor.apply()

        // Restaurar el valor guardado
        val oaci_aterrizaje = sharedPreferences.getString("oaci_aterrizaje", "SK")
        view.findViewById<EditText>(R.id.editText_oaci).setText(oaci_aterrizaje)
        val temp_aterrizaje = sharedPreferences.getString("temp_aterrizaje", "50")
        view.findViewById<EditText>(R.id.editText_temp).setText(temp_aterrizaje)
        val qnh_aterrizaje = sharedPreferences.getString("qnh_aterrizaje", "29.9")
        view.findViewById<EditText>(R.id.editText_qnh).setText(qnh_aterrizaje)

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
            view.findViewById<EditText>(R.id.editText_oaci).setText("SK")
            view.findViewById<EditText>(R.id.editText_temp).setText("50")
            view.findViewById<EditText>(R.id.editText_qnh).setText("29.9")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val oaci_aterrizaje = requireView().findViewById<EditText>(R.id.editText_oaci).text.toString()
        editor.putString("oaci_aterrizaje", oaci_aterrizaje)
        val temp_aterrizaje = requireView().findViewById<EditText>(R.id.editText_temp).text.toString()
        editor.putString("temp_aterrizaje", temp_aterrizaje)
        val qnh_aterrizaje = requireView().findViewById<EditText>(R.id.editText_qnh).text.toString()
        editor.putString("qnh_aterrizaje", qnh_aterrizaje)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}