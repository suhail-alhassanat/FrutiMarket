package com.suhail.frutimarket.apis;

import com.suhail.frutimarket.models.CategoryResponse;
import com.suhail.frutimarket.models.LoginRequest;
import com.suhail.frutimarket.models.LoginResponse;
import com.suhail.frutimarket.models.LogoutResponse;
import com.suhail.frutimarket.models.ProductResponse;
import com.suhail.frutimarket.models.RegisterRequest;
import com.suhail.frutimarket.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient {
    @POST("students/auth/register")
    public Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
    @POST("students/auth/login")
    public Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
    @GET("categories")
    public Call<CategoryResponse> getCategories();
    @GET("categories/{id}/products")
    public Call<ProductResponse> getProductsByCategoryId(@Path("id") int id);
    @GET("students/auth/logout")
    public Call<LogoutResponse> logoutUser(@Header("Authorization") String token);
}
