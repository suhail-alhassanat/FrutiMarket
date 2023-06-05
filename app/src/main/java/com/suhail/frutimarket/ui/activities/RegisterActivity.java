package com.suhail.frutimarket.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.suhail.frutimarket.apis.RequestManager;
import com.suhail.frutimarket.databinding.ActivityRegisterBinding;
import com.suhail.frutimarket.models.RegisterRequest;
import com.suhail.frutimarket.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       binding.btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name=binding.etName.getText().toString();
               String email=binding.etEmail.getText().toString();
               String password=binding.etPassword.getText().toString();
               String gender="M";
               if(binding.rbFemale.isChecked())
                   gender="F";
               Call<RegisterResponse> callRegister= RequestManager.getClient().registerUser(new RegisterRequest(name,email,password,gender));
               callRegister.enqueue(new Callback<RegisterResponse>() {
                   @Override
                   public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                       if(response.isSuccessful()){
                           Toast.makeText(RegisterActivity.this, "Succefull Register", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                           finish();
                       }
                   }

                   @Override
                   public void onFailure(Call<RegisterResponse> call, Throwable t) {
                       Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                   }
               });
           }
       });

    }
}