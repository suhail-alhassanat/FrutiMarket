package com.suhail.frutimarket.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.databinding.FragmentIntroSecondBinding;


public class FragmentIntroSecond extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentIntroSecondBinding binding=FragmentIntroSecondBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}