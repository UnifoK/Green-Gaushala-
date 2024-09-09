package com.example.refining_gaushala_app.network;

import com.example.refining_gaushala_app.models.Bioplant;
import com.example.refining_gaushala_app.models.Gaushala;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/bioplant/register")
    Call<Bioplant> registerBioplant(@Body Bioplant bioplant);

    @POST("api/bioplant/login")
    Call<Bioplant> loginBioplant(@Body Bioplant bioplant);

    @POST("api/gaushala/register")
    Call<Gaushala> registerGaushala(@Body Gaushala gaushala);

    @POST("api/gaushala/login")
    Call<Gaushala> loginGaushala(@Body Gaushala gaushala);
}
