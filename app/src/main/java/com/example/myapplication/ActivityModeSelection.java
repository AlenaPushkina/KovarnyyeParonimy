package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityModeSelectionBinding;


public class ActivityModeSelection extends AppCompatActivity {
    private ActivityModeSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModeSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTest.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityModeSelection.this, TestActivity.class);
            startActivity(intent);
        });

        binding.btnLevel2.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityModeSelection.this, Level2Activity.class);
            startActivity(intent);
        });

        binding.btnLevel1.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityModeSelection.this, Level1Activity.class);
            startActivity(intent);
        });
    }
}
