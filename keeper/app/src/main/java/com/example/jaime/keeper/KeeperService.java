package com.example.jaime.keeper;

import com.example.jaime.keeper.model.Categoria;
import com.example.jaime.keeper.model.Nota;

import java.util.List;

import com.example.jaime.keeper.model.ResponseAuthUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by jaime on 21/02/18.
 */

public interface KeeperService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseAuthUser> doLogin(@Field("email") String email,
                                   @Field("password") String pass
    );

    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseAuthUser> doRegister(@Field("nombre") String nombre,
                                      @Field("email") String email,
                                      @Field("password") String pass
    );

    @GET("nota/lista")
    Call<List<Nota>> listNotes (@Header("X-API-KEY") String token);

    @POST("nota/nueva")
    Call<Nota> addNote (@Header("X-API-KEY") String key,
                          @Field("titulo") String titulo,
                          @Field("descripcion") String descripcion,
                          @Field("idcategoria") String idcategoria
    );

    @FormUrlEncoded
    @POST("nota/editar")
    Call<Nota> editNote (@Header("X-API-KEY") String key,
                         @Field("id") String id_Nota,
                         @Field("titulo") String titulo,
                         @Field("descripcion") String descripcion,
                         @Field("idcategoria") String idcategoria
    );

    @FormUrlEncoded
    @POST("nota/eliminar")
    Call<Nota> deleteNotes (@Header("X-API-KEY") String token,
                            @Field("id") String id_Nota
    );
    @FormUrlEncoded
    @GET("categoria/lista")
    Call<Categoria> listarCategorias(@Header("X-API-KEY") String token);

}
