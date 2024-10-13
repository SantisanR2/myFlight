package com.myflight

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LimitePesoFragment : Fragment(R.layout.limite_peso_fragment) {

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
        listener?.onFragmentInteraction(PesoRealAeronaveFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(PesoAeronaveFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.limite_peso_fragment, container, false)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 6)
        editor.apply()

        // Restaurar el valor guardado
        val carga_req_despegue = sharedPreferences.getString("carga_req_despegue", "0")
        view.findViewById<EditText>(R.id.editText_carga_req_despegue).setText(carga_req_despegue)
        val carga_req_aterrizaje = sharedPreferences.getString("carga_req_aterrizaje", "0")
        view.findViewById<EditText>(R.id.editText_carga_req_aterrizaje).setText(carga_req_aterrizaje)

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
            view.findViewById<EditText>(R.id.editText_carga_req_despegue).setText("0")
            view.findViewById<EditText>(R.id.editText_carga_req_aterrizaje).setText("0")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val carga_req_despegue = requireView().findViewById<EditText>(R.id.editText_carga_req_despegue).text.toString()
        editor.putString("carga_req_despegue", carga_req_despegue)
        val carga_req_aterrizaje = requireView().findViewById<EditText>(R.id.editText_carga_req_aterrizaje).text.toString()
        editor.putString("carga_req_aterrizaje", carga_req_aterrizaje)
        editor.apply()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}