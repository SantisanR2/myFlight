package com.myflight

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriftdownFragment : Fragment(R.layout.driftdown_fragment) {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var apiService: ApiService
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        listener?.onFragmentInteraction(TablaCalculosFragment())
    }

    private fun previousFragment() {
        listener?.onFragmentInteraction(PesoRealAeronaveFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.driftdown_fragment, container, false)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://santisanr2.pythonanywhere.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Guardar el fragmento actual
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 8)
        editor.apply()

        // Restaurar el valor guardado
        val peso_despegue = sharedPreferences.getString("peso_despegue", "0")
        view.findViewById<EditText>(R.id.editText_peso_despegue).setText(peso_despegue)
        val fuel_consumido = sharedPreferences.getString("fuel_consumido", "0")
        view.findViewById<EditText>(R.id.editText_fuel_consumido).setText(fuel_consumido)
        val qnh_act = sharedPreferences.getString("qnh_act", "0")
        view.findViewById<EditText>(R.id.editText_qnh_act).setText(qnh_act)
        val oat = sharedPreferences.getString("oat", "0")
        view.findViewById<EditText>(R.id.editText_oat).setText(oat)
        val fl = sharedPreferences.getString("fl", "0")
        view.findViewById<EditText>(R.id.editText_fl).setText(fl)

        val fab: FloatingActionButton = view.findViewById(R.id.fab_next)
        fab.setOnClickListener {
            saveData()
            sendInputData()
        }

        val fab1: FloatingActionButton = view.findViewById(R.id.fab_previous)
        fab1.setOnClickListener {
            saveData()
            previousFragment()
        }

        val fab2: FloatingActionButton = view.findViewById(R.id.fab_clear)
        fab2.setOnClickListener{
            view.findViewById<EditText>(R.id.editText_peso_despegue).setText("9481")
            view.findViewById<EditText>(R.id.editText_fuel_consumido).setText("150")
            view.findViewById<EditText>(R.id.editText_qnh_act).setText("29.92")
            view.findViewById<EditText>(R.id.editText_oat).setText("10")
            view.findViewById<EditText>(R.id.editText_fl).setText("0")
        }

        return view
    }

    fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val peso_despegue = requireView().findViewById<EditText>(R.id.editText_peso_despegue).text.toString()
        editor.putString("peso_despegue", peso_despegue)
        val fuel_consumido = requireView().findViewById<EditText>(R.id.editText_fuel_consumido).text.toString()
        editor.putString("fuel_consumido", fuel_consumido)
        val qnh_act = requireView().findViewById<EditText>(R.id.editText_qnh_act).text.toString()
        editor.putString("qnh_act", qnh_act)
        val oat = requireView().findViewById<EditText>(R.id.editText_oat).text.toString()
        editor.putString("oat", oat)
        val fl = requireView().findViewById<EditText>(R.id.editText_fl).text.toString()
        editor.putString("fl", fl)
        editor.apply()
    }

    private fun sendInputData() {
        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)

        val first_flight_text = sharedPreferences.getString("first_flight", "0")
        var first_flight = false
        when (first_flight_text) {
            "si" -> first_flight = true
            "no" -> first_flight = false
        }
        val mn_min = sharedPreferences.getString("mn_min", "0")?.toFloat() ?: 0.0f
        val f_f = sharedPreferences.getString("f_f", "0")?.toFloat() ?: 0.0f
        val distancia_destino = sharedPreferences.getString("distancia_destino", "0")?.toFloat() ?: 0.0f
        val distancia_alterno = sharedPreferences.getString("distancia_alterno", "0")?.toFloat() ?: 0.0f
        val oaci_despegue = sharedPreferences.getString("oaci_despegue", "")
        val temp_despegue = sharedPreferences.getString("temp_despegue", "0")?.toInt() ?: 0
        val qnh_despegue = sharedPreferences.getString("qnh_despegue", "0")?.toFloat() ?: 0.0f
        val oaci_aterrizaje = sharedPreferences.getString("oaci_aterrizaje", "")
        val temp_aterrizaje = sharedPreferences.getString("temp_aterrizaje", "0")?.toInt() ?: 0
        val qnh_aterrizaje = sharedPreferences.getString("qnh_aterrizaje", "0")?.toFloat() ?: 0.0f
        val CAD = sharedPreferences.getString("CAD", "0")?.toFloat() ?: 0.0f
        val CS = sharedPreferences.getString("CS", "0")?.toFloat() ?: 0.0f
        val trip = sharedPreferences.getString("trip", "0")?.toFloat() ?: 0.0f
        val aeronave = sharedPreferences.getString("aeronave", "0")?.toFloat() ?: 0.0f
        val carga_req_despegue = sharedPreferences.getString("carga_req_despegue", "0")?.toFloat() ?: 0.0f
        val carga_req_aterrizaje = sharedPreferences.getString("carga_req_aterrizaje", "0")?.toFloat() ?: 0.0f
        val peso_carga = sharedPreferences.getString("peso_carga", "0")?.toFloat() ?: 0.0f
        val cantidad_pax_200 = sharedPreferences.getString("cantidad_pax_200", "0")?.toFloat() ?: 0.0f
        val cantidad_pax_220 = sharedPreferences.getString("cantidad_pax_220", "0")?.toFloat() ?: 0.0f
        val cantidad_pax_250 = sharedPreferences.getString("cantidad_pax_250", "0")?.toFloat() ?: 0.0f
        val total_peso_pax_manual = sharedPreferences.getString("total_peso_pax_manual", "0")?.toFloat() ?: 0.0f
        val peso_despegue = sharedPreferences.getString("peso_despegue", "0")?.toFloat() ?: 0.0f
        val fuel_consumido = sharedPreferences.getString("fuel_consumido", "0")?.toFloat() ?: 0.0f
        val qnh_act = sharedPreferences.getString("qnh_act", "0")?.toFloat() ?: 0.0f
        val oat = sharedPreferences.getString("oat", "0")?.toFloat() ?: 0.0f
        val fl = sharedPreferences.getString("fl", "0")?.toFloat() ?: 0.0f

        var data = InputData(true,0.0f,0.0f,0.0f,0.0f,"",0,0.0f,"",0,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f)

        if(oaci_despegue != null && oaci_aterrizaje != null) {
            data = InputData(first_flight, mn_min, f_f, distancia_destino, distancia_alterno, oaci_despegue, temp_despegue, qnh_despegue, oaci_aterrizaje, temp_aterrizaje, qnh_aterrizaje, CAD, CS, trip, aeronave, carga_req_despegue, carga_req_aterrizaje, peso_carga, cantidad_pax_200, cantidad_pax_220, cantidad_pax_250, total_peso_pax_manual, peso_despegue, fuel_consumido, qnh_act, oat, fl)
        }

        if (isInternetAvailable(requireContext())) {
            sendData(data)
            nextFragment()
        }
        else
        {
            showAlertDialog("Error", "No hay conexión a internet. Inténtalo de nuevo cuando tengas conexión. Los datos ingresados, se han guardado correctamente.")
        }
    }

    private fun sendData(data: InputData) {
        val call = apiService.sendInputData(data)

        call.enqueue(object : Callback<OutputData> {
            override fun onResponse(call: Call<OutputData>, response: Response<OutputData>) {
                println(response)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Se envió exitosamente la información", Toast.LENGTH_SHORT).show()
                    val outputData = response.body()
                    sharedViewModel.setOutputData(outputData!!)
                } else {
                    Toast.makeText(context, "No se pudo enviar la información", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OutputData>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}