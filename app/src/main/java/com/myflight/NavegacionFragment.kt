package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NavegacionFragment : Fragment(R.layout.navegacion_fragment){

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
        listener?.onFragmentInteraction(AeropuertoDespegueFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.navegacion_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 1)
        editor.apply()

        // Restaurar el valor guardado
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        val primerVuelo = sharedPreferences.getString("first_flight", "")
        when (primerVuelo) {
            "si" -> radioGroup.check(R.id.primer_vuelo_si_radio)
            "no" -> radioGroup.check(R.id.primer_vuelo_no_radio)
        }
        val mn = sharedPreferences.getString("mn_min", "0")
        view.findViewById<EditText>(R.id.editText_mn).setText(mn)
        val f_f = sharedPreferences.getString("f_f", "0")
        view.findViewById<EditText>(R.id.editText_f_f).setText(f_f)
        val distancia_destino = sharedPreferences.getString("distancia_destino", "0")
        view.findViewById<EditText>(R.id.editText_distancia_destino).setText(distancia_destino)
        val distancia_alterno = sharedPreferences.getString("distancia_alterno", "100")
        view.findViewById<EditText>(R.id.editText_distancia_alterno).setText(distancia_alterno)

        // Guardar el valor seleccionado
        val fab: FloatingActionButton = view.findViewById(R.id.fab_next)
        fab.setOnClickListener {
            saveData()
            nextFragment()
        }

        val fab1: FloatingActionButton = view.findViewById(R.id.fab_clear)
        fab1.setOnClickListener{
            view.findViewById<EditText>(R.id.editText_mn).setText("2.7")
            view.findViewById<EditText>(R.id.editText_f_f).setText("700")
            view.findViewById<EditText>(R.id.editText_distancia_destino).setText("0")
            view.findViewById<EditText>(R.id.editText_distancia_alterno).setText("0")
            radioGroup.check(R.id.primer_vuelo_si_radio)
        }



        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val radioGroup: RadioGroup = requireView().findViewById(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        when (selectedRadioButtonId) {
            R.id.primer_vuelo_si_radio -> editor.putString("first_flight", "si")
            R.id.primer_vuelo_no_radio -> editor.putString("first_flight", "no")
        }
        val mn = requireView().findViewById<EditText>(R.id.editText_mn).text.toString()
        editor.putString("mn_min", mn)
        val f_f = requireView().findViewById<EditText>(R.id.editText_f_f).text.toString()
        editor.putString("f_f", f_f)
        val distancia_destino = requireView().findViewById<EditText>(R.id.editText_distancia_destino).text.toString()
        editor.putString("distancia_destino", distancia_destino)
        val distancia_alterno = requireView().findViewById<EditText>(R.id.editText_distancia_alterno).text.toString()
        editor.putString("distancia_alterno", distancia_alterno)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}