package com.suhail.frutimarket.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.adapters.ViewPagerAdapter;
import com.suhail.frutimarket.databinding.ActivityIntroBinding;
import com.suhail.frutimarket.ui.fragments.FragmentIntroFirst;
import com.suhail.frutimarket.ui.fragments.FragmentIntroSecond;
import com.suhail.frutimarket.ui.fragments.FragmentIntroThird;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
ActivityIntroBinding binding;
private int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Fragment> fragments=new ArrayList<Fragment>();
        ViewPagerAdapter adapter=new ViewPagerAdapter(this);
        adapter.addFragment(new FragmentIntroFirst());
        adapter.addFragment(new FragmentIntroSecond());
        adapter.addFragment(new FragmentIntroThird());
        binding.viewpager.setAdapter(adapter);
        binding.introIndicator.setViewPager2(binding.viewpager);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pos=position;
                if(pos==2)
                {
                    binding.btnNext.setText("Get Started");
                }
                else
                    binding.btnNext.setText("next");
            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(pos<2){
              binding.viewpager.setCurrentItem(pos+1);
               }
               else
               {
                   startActivity(new Intent(IntroActivity.this,LoginActivity.class));
               }

            }
        });
        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,LoginActivity.class));
            }
        });
    }
}