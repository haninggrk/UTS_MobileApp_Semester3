package com.hgrk.moviedbuts.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hgrk.moviedbuts.R;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container_main);
        NavController navCo = navHostFragment.getNavController();
    }
}