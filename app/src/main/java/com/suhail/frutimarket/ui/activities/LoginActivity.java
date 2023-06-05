package com.suhail.frutimarket.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.suhail.frutimarket.apis.RequestManager;
import com.suhail.frutimarket.databinding.ActivityLoginBinding;
import com.suhail.frutimarket.models.LoginRequest;
import com.suhail.frutimarket.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    public static final String EMAIL_KEY="email",PASSWORD_KEY="password",TOKEN_KEY="Token";
    private  SharedPreferences loginPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Logging in...");
        loginPreferences =getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        if(isLoggedIn()){
//            dialog.show();
            login(getLoginDetails());
        }
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.etEmail.getText().toString();
                String password=binding.etPassword.getText().toString();
                if(!(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))){
                login(new LoginRequest(email,password));
            }
            else
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
            }
        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
    private void login(LoginRequest request){
        Call<LoginResponse> callLogin= RequestManager.getClient().loginUser(request);
        callLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    saveLoginDetails(request);
                    Toast.makeText(LoginActivity.this, "login succefull", Toast.LENGTH_SHORT).show();
                    LoginResponse response1=response.body();
                    SharedPreferences.Editor editor = loginPreferences.edit();
                    editor.putString(TOKEN_KEY,"Token "+response1.getObject().getToken());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("data",response1));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Login methods
    public    boolean isLoggedIn(){
        if(loginPreferences.getString(EMAIL_KEY,"null").equals("null") || loginPreferences.getString(PASSWORD_KEY,"null").equals("null"))
            return false;
        return true;
    }
    public LoginRequest getLoginDetails(){
        if(isLoggedIn())
            return new LoginRequest(loginPreferences.getString(EMAIL_KEY,"null"), loginPreferences.getString(PASSWORD_KEY,"null"));
        return null;
    }
    public void saveLoginDetails(LoginRequest request){
        SharedPreferences.Editor editor= loginPreferences.edit();
        editor.putString(EMAIL_KEY,request.getEmail());
        editor.putString(PASSWORD_KEY,request.getPassword());
        editor.apply();
    }
}