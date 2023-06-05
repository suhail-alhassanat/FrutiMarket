package com.suhail.frutimarket.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.suhail.frutimarket.R;
import com.suhail.frutimarket.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    private AppBarConfiguration configuration;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
initNavigation();

    }
    private void initNavigation(){
        configuration= new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_cart,R.id.nav_fav,R.id.nav_account
        ).build();
        navController= Navigation.findNavController(this,R.id.nav_host_name);
        NavigationUI.setupWithNavController(binding.bottomNav,navController);
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btn_home:
                        navController.navigate(R.id.nav_home);
                        break;
                    case R.id.btn_fav:
                        navController.navigate(R.id.nav_fav);
                        break;
                    case R.id.btn_cart:
                        navController.navigate(R.id.nav_cart);
                        break;
                    case R.id.btn_account:
                        navController.navigate(R.id.nav_account);
                        break;
                }
                return true;
            }
        });
    }
}