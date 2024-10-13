package com.myflight

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myflight.databinding.ActivityInputBinding


class InputActivity : AppCompatActivity(), NavegacionFragment.OnFragmentInteractionListener, AeropuertoDespegueFragment.OnFragmentInteractionListener, AeropuertoAterrizajeFragment.OnFragmentInteractionListener, PesoRealAeronaveFragment.OnFragmentInteractionListener, DriftdownFragment.OnFragmentInteractionListener, CombustibleFragment.OnFragmentInteractionListener, PesoAeronaveFragment.OnFragmentInteractionListener, LimitePesoFragment.OnFragmentInteractionListener {

    lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setTheme(R.style.Theme_MyFlight)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding = ActivityInputBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val currentFragmentIndex = sharedPreferences.getInt("current_fragment", 1)
        val fragment = when (currentFragmentIndex) {
            2 -> AeropuertoDespegueFragment()
            3 -> AeropuertoAterrizajeFragment()
            4 -> CombustibleFragment()
            5 -> PesoAeronaveFragment()
            6 -> LimitePesoFragment()
            7 -> PesoRealAeronaveFragment()
            8 -> DriftdownFragment()
            else -> NavegacionFragment()
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Estás seguro de que quieres desechar los datos actuales?")
                .setPositiveButton("Sí") { dialog, which ->
                    val editor = sharedPreferences.edit()
                    editor.clear().apply()

                    val navFragment = NavegacionFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, navFragment)
                        .addToBackStack(null)
                        .commit()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onFragmentInteraction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}