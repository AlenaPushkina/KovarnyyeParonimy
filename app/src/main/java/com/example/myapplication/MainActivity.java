package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;



import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ActivityModeSelection.class);
            startActivity(intent);
        });

        binding.btnInstruction.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, Instruction.class);
            startActivity(intent);
});


    }
}