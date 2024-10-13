package com.myflight

import com.google.gson.annotations.SerializedName

data class InputData(
    @SerializedName("first_flight") val first_flight: Boolean,
    @SerializedName("mn_min") val mn_min: Float,
    @SerializedName("f_f") val f_f: Float,
    @SerializedName("distancia_destino") val distancia_destino: Float,
    @SerializedName("distancia_alterno") val distancia_alterno: Float,
    @SerializedName("oaci_despegue") val oaci_despegue: String,
    @SerializedName("temp_despegue") val temp_despegue: Int,
    @SerializedName("qnh_despegue") val qnh_despegue: Float,
    @SerializedName("oaci_aterrizaje") val oaci_aterrizaje: String,
    @SerializedName("temp_aterrizaje") val temp_aterrizaje: Int,
    @SerializedName("qnh_aterrizaje") val qnh_aterrizaje: Float,
    @SerializedName("CAD") val CAD: Float,
    @SerializedName("CS") val CS: Float,
    @SerializedName("trip") val trip: Float,
    @SerializedName("aeronave") val aeronave: Float,
    @SerializedName("carga_req_despegue") val carga_req_despegue: Float,
    @SerializedName("carga_req_aterrizaje") val carga_req_aterrizaje: Float,
    @SerializedName("peso_carga") val peso_carga: Float,
    @SerializedName("cantidad_pax_200") val cantidad_pax_200: Float,
    @SerializedName("cantidad_pax_220") val cantidad_pax_220: Float,
    @SerializedName("cantidad_pax_250") val cantidad_pax_250: Float,
    @SerializedName("total_peso_pax_manual") val total_peso_pax_manual: Float,
    @SerializedName("peso_despegue") val peso_despegue: Float,
    @SerializedName("fuel_consumido") val fuel_consumido: Float,
    @SerializedName("qnh_act") val qnh_act: Float,
    @SerializedName("oat") val oat: Float,
    @SerializedName("fl") val fl: Float
)
