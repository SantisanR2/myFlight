package com.myflight

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("calcular")
    fun sendInputData(@Body inputData: InputData): Call<OutputData>

    @POST("login")
    fun sendLoginData(@Body loginData: LoginData): Call<Void>
}
