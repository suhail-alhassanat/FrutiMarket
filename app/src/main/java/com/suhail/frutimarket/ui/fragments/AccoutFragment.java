package com.suhail.frutimarket.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.adapters.OptionsRVAdapter;
import com.suhail.frutimarket.apis.RequestManager;
import com.suhail.frutimarket.databinding.FragmentAccoutBinding;
import com.suhail.frutimarket.listeners.OnOptionClickedListener;
import com.suhail.frutimarket.models.LogoutResponse;
import com.suhail.frutimarket.models.OptionsItem;
import com.suhail.frutimarket.ui.activities.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccoutFragment extends Fragment {
    private  final String EMAIL_KEY="email",PASSWORD_KEY="password";
    private SharedPreferences loginPreferences;
    public AccoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPreferences =getActivity().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAccoutBinding binding=FragmentAccoutBinding.inflate(getLayoutInflater());
        List<OptionsItem> list=new ArrayList<>();
        list.add(new OptionsItem(R.drawable.ic_orders,"My Orders"));
        list.add(new OptionsItem(R.drawable.ic_settings,"Settings"));
        list.add(new OptionsItem(R.drawable.ic_cart,"My Cart"));
        list.add(new OptionsItem(R.drawable.ic_help,"Help"));
        list.add(new OptionsItem(R.drawable.ic_logout,"Log Out"));
        binding.rvOptions.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvOptions.setAdapter(new OptionsRVAdapter(list, getActivity(), new OnOptionClickedListener() {
            @Override
            public void onOptionClicked(String option) {
                switch (option){
                    case "Log Out":
                        logOut();
                        break;
                    default:
                        Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
                }
            }
        }));
        binding.rvOptions.setHasFixedSize(true);
        return binding.getRoot();
    }
    private void logOut(){
        SharedPreferences.Editor editor=loginPreferences.edit();
        editor.clear();
        editor.commit();
        Call<LogoutResponse> callLogout = RequestManager.getClient().logoutUser(loginPreferences.getString(LoginActivity.TOKEN_KEY,""));
        callLogout.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: "+response.body().getMessage());
            }
                else
                    Log.d("TAG", "onResponseFail: unsuccessful");

            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}