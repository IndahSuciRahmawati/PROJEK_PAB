package com.projectakhir.betamart;

import com.projectakhir.betamart.Data.GetBarangResponse;
import com.projectakhir.betamart.Data.GetPesananResponse;
import com.projectakhir.betamart.Data.GetUserResponse;
import com.projectakhir.betamart.Data.InputPesananResponse;
import com.projectakhir.betamart.Data.LoginResponse;
import com.projectakhir.betamart.Data.RegisterResponse;
import com.projectakhir.betamart.Data.UpdateUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("GetData.php")
    Call<GetBarangResponse> getDataBarang();

    @FormUrlEncoded
    @POST("PesanBarang.php")
    Call<InputPesananResponse> pesanBarang(
            @Field("id_barang") String idBarang,
            @Field("nama_penerima") String namaPenerima,
            @Field("jumlah_pesanan") int jumlahPesanan,
            @Field("alamat") String alamat,
            @Field("nomor_hp") String nomorHp,
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("GetPemesanan.php")
    Call<GetPesananResponse> getPesananBarang(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("UpdateUser.php")
    Call<UpdateUserResponse> updateUser(
            @Field("id") int id,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("role") String role
    );

    @FormUrlEncoded
    @POST("GetUser.php")
    Call<GetUserResponse> getUserById(
            @Field("id") int id
    );

}



