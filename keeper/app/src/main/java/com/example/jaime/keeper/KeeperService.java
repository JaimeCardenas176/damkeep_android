package com.example.jaime.keeper;

import model.ResponseAuthUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jaime on 21/02/18.
 */

public interface KeeperService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseAuthUser> doLogin(@Field("email") String email,@Field("password") String pass);

    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseAuthUser> doRegister(@Field("nombre") String nombre,@Field("email") String email,@Field("password") String pass);

}
