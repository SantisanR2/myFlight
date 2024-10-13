package com.myflight

import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import android.text.SpannableStringBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TablaCalculosFragment : Fragment(R.layout.tabla_calculos_fragment) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tabla_calculos_fragment, container, false)


        val sharedPreferences = requireActivity().getSharedPreferences("form_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("current_fragment", 1)
        editor.apply()

        // Observa los datos del ViewModel
        sharedViewModel.outputData.observe(viewLifecycleOwner, Observer { outputData ->
            // Muestra los datos en la UI
            outputData?.let {
                if(it.first_flight) {view.findViewById<EditText>(R.id.editText_primer_vuelo).setText("Si")}
                else {view.findViewById<EditText>(R.id.editText_primer_vuelo).setText("No")}
                view.findViewById<EditText>(R.id.editText_ca).setText(it.CA.toString())
                view.findViewById<EditText>(R.id.editText_cad).setText(it.CAD.toString())
                view.findViewById<EditText>(R.id.editText_cd).setText(it.CD.toString())
                view.findViewById<EditText>(R.id.editText_ce).setText(it.CE.toString())
                view.findViewById<EditText>(R.id.editText_cmrn).setText(it.CMRN.toString())
                view.findViewById<EditText>(R.id.editText_cr).setText(it.CR)
                view.findViewById<EditText>(R.id.editText_crn).setText(it.CRN.toString())
                view.findViewById<EditText>(R.id.editText_cs).setText(it.CS.toString())
                view.findViewById<EditText>(R.id.editText_fuel_destino_lbs).setText(it.FUEL_EN_EL_DESTINO_LBS.toString())
                view.findViewById<EditText>(R.id.editText_fuel_destino_usg).setText(it.FUEL_EN_EL_DESTINO_USG.toString())
                view.findViewById<EditText>(R.id.editText_tmrn).setText(it.TMRN.toString())
                view.findViewById<EditText>(R.id.editText_tmrn_en).setText(it.TMRN_EN.toString())
                view.findViewById<EditText>(R.id.editText_aeronave).setText(it.aeronave.toString())
                view.findViewById<EditText>(R.id.editText_aeronave_trip).setText(it.aeronave_tripulacion.toString())
                view.findViewById<EditText>(R.id.editText_aeronave_trip_fuel).setText(it.aeronave_tripulacion_combustible.toString())
                view.findViewById<EditText>(R.id.editText_alt_den).setText(it.alt_den.toString())
                view.findViewById<EditText>(R.id.editText_alt_pres_aterrizaje).setText(it.alt_pres_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_alt_pres_despegue).setText(it.alt_pres_despegue.toString())
                view.findViewById<EditText>(R.id.editText_alt_pres).setText(it.alt_pres_driftdown.toString())
                view.findViewById<EditText>(R.id.editText_ancho_mts_aterrizaje).setText(it.ancho_aterrizaje)
                view.findViewById<EditText>(R.id.editText_ancho_mts_despegue).setText(it.ancho_despegue)
                view.findViewById<EditText>(R.id.editText_asda_1_mts_aterrizaje).setText(it.asda_1_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_asda_1_ft_aterrizaje).setText(it.asda_1_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_asda_1_mts_despegue).setText(it.asda_1_despegue.toString())
                view.findViewById<EditText>(R.id.editText_asda_1_ft_despegue).setText(it.asda_1_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_asda_2_mts_aterrizaje).setText(it.asda_2_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_asda_2_ft_aterrizaje).setText(it.asda_2_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_asda_2_mts_despegue).setText(it.asda_2_despegue.toString())
                view.findViewById<EditText>(R.id.editText_asda_2_ft_despegue).setText(it.asda_2_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_cantidad_pax_200).setText(it.cantidad_pax_200.toString())
                view.findViewById<EditText>(R.id.editText_cantidad_pax_220).setText(it.cantidad_pax_220.toString())
                view.findViewById<EditText>(R.id.editText_cantidad_pax_250).setText(it.cantidad_pax_250.toString())
                view.findViewById<EditText>(R.id.editText_carga_disponible_limitado_aterrizaje).setText(it.carga_disponible_limitado_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_carga_disponible_limitado_despegue).setText(it.carga_disponible_limitado_despegue.toString())
                view.findViewById<EditText>(R.id.editText_carga_aterrizaje).setText(it.carga_paga_limitada_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_carga_despegue).setText(it.carga_paga_limitada_despegue.toString())
                view.findViewById<EditText>(R.id.editText_carga_req_aterrizaje).setText(it.carga_req_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_carga_req_despegue).setText(it.carga_req_despegue.toString())
                view.findViewById<EditText>(R.id.editText_ciudad_aterrizaje).setText(it.ciudad_aterrizaje)
                view.findViewById<EditText>(R.id.editText_ciudad_despegue).setText(it.ciudad_despegue)
                view.findViewById<EditText>(R.id.editText_distancia_alterno).setText(it.distancia_alterno.toString())
                view.findViewById<EditText>(R.id.editText_distancia_destino).setText(it.distancia_destino.toString())
                view.findViewById<EditText>(R.id.editText_elevacion_aterrizaje).setText(it.elevacion_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_elevacion_despegue).setText(it.elevacion_despegue.toString())
                view.findViewById<EditText>(R.id.editText_f_f).setText(it.f_f.toString())
                view.findViewById<EditText>(R.id.editText_fl).setText(it.fl.toString())
                view.findViewById<EditText>(R.id.editText_fuel_consumido).setText(it.fuel_consumido.toString())
                view.findViewById<EditText>(R.id.editText_lda_1_mts_aterrizaje).setText(it.lda_1_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_lda_1_ft_aterrizaje).setText(it.lda_1_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_lda_1_mts_despegue).setText(it.lda_1_despegue.toString())
                view.findViewById<EditText>(R.id.editText_lda_1_ft_despegue).setText(it.lda_1_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_lda_2_mts_aterrizaje).setText(it.lda_2_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_lda_2_ft_aterrizaje).setText(it.lda_2_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_lda_2_mts_despegue).setText(it.lda_2_despegue.toString())
                view.findViewById<EditText>(R.id.editText_lda_2_ft_despegue).setText(it.lda_2_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_ldg_weight_despegue).setText(it.ldg_weight_despegue.toString())
                view.findViewById<EditText>(R.id.editText_ldg_weight_aterrizaje).setText(it.max_ldg_weight_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_max_to_weight_despegue).setText(it.max_to_weight_despegue.toString())
                view.findViewById<EditText>(R.id.editText_mn_min).setText(it.mn_min.toString())
                view.findViewById<EditText>(R.id.editText_oaci_aterrizaje).setText(it.oaci_aterrizaje)
                view.findViewById<EditText>(R.id.editText_oaci_despegue).setText(it.oaci_despegue)
                view.findViewById<EditText>(R.id.editText_oat).setText(it.oat.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_200_aterrizaje).setText(it.pasajeros_200_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_200_despegue).setText(it.pasajeros_200_despegue.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_220_aterrizaje).setText(it.pasajeros_220_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_220_despegue).setText(it.pasajeros_220_despegue.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_250_aterrizaje).setText(it.pasajeros_250_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_pasajeros_250_despegue).setText(it.pasajeros_250_despegue.toString())
                view.findViewById<EditText>(R.id.editText_peso_actual_kg).setText(it.peso_actual_kg.toString())
                view.findViewById<EditText>(R.id.editText_peso_actual_lbs).setText(it.peso_actual_lbs.toString())
                view.findViewById<EditText>(R.id.editText_peso_carga).setText(it.peso_carga.toString())
                view.findViewById<EditText>(R.id.editText_peso_despegue_driftdown).setText(it.peso_despegue.toString())
                view.findViewById<EditText>(R.id.editText_peso_real_actual_aeronave_kg).setText(it.peso_real_actual_kg.toString())
                view.findViewById<EditText>(R.id.editText_peso_real_actual_aeronave_lbs).setText(it.peso_real_actual_lbs.toString())
                view.findViewById<EditText>(R.id.editText_qnh_act).setText(it.qnh_act.toString())
                view.findViewById<EditText>(R.id.editText_qnh_aterrizaje).setText(it.qnh_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_qnh_despegue).setText(it.qnh_despegue.toString())
                view.findViewById<EditText>(R.id.editText_rwy_1_aterrizaje).setText(it.rwy_1_aterrizaje)
                view.findViewById<EditText>(R.id.editText_rwy_1_despegue).setText(it.rwy_1_despegue)
                view.findViewById<EditText>(R.id.editText_rwy_2_aterrizaje).setText(it.rwy_2_aterrizaje)
                view.findViewById<EditText>(R.id.editText_rwy_2_despegue).setText(it.rwy_2_despegue)
                view.findViewById<EditText>(R.id.editText_temp_aterrizaje).setText(it.temp_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_temp_despegue).setText(it.temp_despegue.toString())
                view.findViewById<EditText>(R.id.editText_t_isa_ideal).setText(it.temp_isa_ideal.toString())
                view.findViewById<EditText>(R.id.editText_t_isa_real).setText(it.temp_isa_real.toString())
                view.findViewById<EditText>(R.id.editText_temp_ref_aterrizaje).setText(it.temp_ref_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_temp_ref_despegue).setText(it.temp_ref_despegue.toString())
                view.findViewById<EditText>(R.id.editText_tiempo_alterno).setText(it.tiempo_alterno)
                view.findViewById<EditText>(R.id.editText_tiempo_destino).setText(it.tiempo_destino)
                view.findViewById<EditText>(R.id.editText_max_to_weight_aterrizaje).setText(it.to_weight_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_toda_1_mts_aterrizaje).setText(it.toda_1_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_toda_1_ft_aterrizaje).setText(it.toda_1_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_toda_1_mts_despegue).setText(it.toda_1_despegue.toString())
                view.findViewById<EditText>(R.id.editText_toda_1_ft_despegue).setText(it.toda_1_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_toda_2_mts_aterrizaje).setText(it.toda_2_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_toda_2_ft_aterrizaje).setText(it.toda_2_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_toda_2_mts_despegue).setText(it.toda_2_despegue.toString())
                view.findViewById<EditText>(R.id.editText_toda_2_ft_despegue).setText(it.toda_2_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_tora_1_mts_aterrizaje).setText(it.tora_1_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_tora_1_ft_aterrizaje).setText(it.tora_1_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_tora_1_mts_despegue).setText(it.tora_1_despegue.toString())
                view.findViewById<EditText>(R.id.editText_tora_1_ft_despegue).setText(it.tora_1_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_tora_2_mts_aterrizaje).setText(it.tora_2_aterrizaje.toString())
                view.findViewById<EditText>(R.id.editText_tora_2_ft_aterrizaje).setText(it.tora_2_aterrizaje_ft.toString())
                view.findViewById<EditText>(R.id.editText_tora_2_mts_despegue).setText(it.tora_2_despegue.toString())
                view.findViewById<EditText>(R.id.editText_tora_2_ft_despegue).setText(it.tora_2_despegue_ft.toString())
                view.findViewById<EditText>(R.id.editText_total_carga_pax).setText(it.total_carga_pax.toString())
                view.findViewById<EditText>(R.id.editText_total_peso_pax_manual).setText(it.total_peso_pax_manual.toString())
                view.findViewById<EditText>(R.id.editText_total_peso_pax_promediado).setText(it.total_peso_pax_promedio.toString())
                view.findViewById<EditText>(R.id.editText_trip).setText(it.trip.toString())
                view.findViewById<EditText>(R.id.editText_vel_crucero).setText(it.vel_crucero.toString())
                view.findViewById<EditText>(R.id.editText_zero_fuel).setText(it.zero_fuel.toString())
            }

            val fabInfo: FloatingActionButton = view.findViewById(R.id.fab_info)
            fabInfo.setOnClickListener {
                showInfoDialog()
            }
        })

        return view
    }

    private fun showInfoDialog() {
        val message = SpannableStringBuilder()
            .append("• CR: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible de rodaje\n")
            .append("• CD: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible de destino\n")
            .append("• CA: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible alterno\n")
            .append("• CRN: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible reserva nacional (45', 525 Lbs, 700 Lbs/Hr)\n")
            .append("• CE: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible de emergencia (15', 200 Lbs, 800 Lbs/Hr)\n")
            .append("• CMRN: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible mínimo requerido nacional\n")
            .append("• CAD: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible adicional (20', 234 Lbs, 700 Lbs/Hr)\n")
            .append("• CS: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Combustible de sostenimiento (ATC/NOTAM: 45', 423 Lbs, 500 Lbs/Hr - METEO: 20', 184 Lbs, 550 Lbs/Hr)\n")
            .append("• TMRN: ", StyleSpan(Typeface.BOLD), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            .append("Total mínimo requerido nacional")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Glosario")
        builder.setMessage(message)
        builder.setPositiveButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}