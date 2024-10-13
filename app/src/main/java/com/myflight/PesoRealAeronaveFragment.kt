package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PesoRealAeronaveFragment : Fragment(R.layout.peso_real_aeronave_fragment) {

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
        listener?.onFragmentInteraction(DriftdownFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(LimitePesoFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.peso_real_aeronave_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 7)
        editor.apply()

        // Restaurar el valor guardado
        val peso_carga = sharedPreferences.getString("peso_carga", "0")
        view.findViewById<EditText>(R.id.editText_peso_carga).setText(peso_carga)
        val cantidad_pax_200 = sharedPreferences.getString("cantidad_pax_200", "0")
        view.findViewById<EditText>(R.id.editText_cantidad_pax_200).setText(cantidad_pax_200)
        val cantidad_pax_220 = sharedPreferences.getString("cantidad_pax_220", "0")
        view.findViewById<EditText>(R.id.editText_cantidad_pax_220).setText(cantidad_pax_220)
        val cantidad_pax_250 = sharedPreferences.getString("cantidad_pax_250", "0")
        view.findViewById<EditText>(R.id.editText_cantidad_pax_250).setText(cantidad_pax_250)
        val total_peso_pax_manual = sharedPreferences.getString("total_peso_pax_manual", "0")
        view.findViewById<EditText>(R.id.editText_total_peso_pax_manual).setText(total_peso_pax_manual)

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
            view.findViewById<EditText>(R.id.editText_peso_carga).setText("0")
            view.findViewById<EditText>(R.id.editText_cantidad_pax_200).setText("0")
            view.findViewById<EditText>(R.id.editText_cantidad_pax_220).setText("0")
            view.findViewById<EditText>(R.id.editText_cantidad_pax_250).setText("0")
            view.findViewById<EditText>(R.id.editText_total_peso_pax_manual).setText("0")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val peso_carga = view?.findViewById<EditText>(R.id.editText_peso_carga)?.text.toString()
        editor.putString("peso_carga", peso_carga)
        val cantidad_pax_200 = view?.findViewById<EditText>(R.id.editText_cantidad_pax_200)?.text.toString()
        editor.putString("cantidad_pax_200", cantidad_pax_200)
        val cantidad_pax_220 = view?.findViewById<EditText>(R.id.editText_cantidad_pax_220)?.text.toString()
        editor.putString("cantidad_pax_220", cantidad_pax_220)
        val cantidad_pax_250 = view?.findViewById<EditText>(R.id.editText_cantidad_pax_250)?.text.toString()
        editor.putString("cantidad_pax_250", cantidad_pax_250)
        val total_peso_pax_manual = view?.findViewById<EditText>(R.id.editText_total_peso_pax_manual)?.text.toString()
        editor.putString("total_peso_pax_manual", total_peso_pax_manual)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}