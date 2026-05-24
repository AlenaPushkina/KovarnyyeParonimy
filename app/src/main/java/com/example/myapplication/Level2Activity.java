package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.Level1ActivityBinding;
import com.example.myapplication.databinding.Level2ActivityBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Level2Activity extends AppCompatActivity {
    private Level2ActivityBinding binding;
    private ArrayList<SubLevel> subLevels;
    private int currentLevelIndex2 = 0;
    private int wrongAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Level2ActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSubLevels();
        displayCurrentSubLevel();

        binding.word2.addTextChangedListener(new android.text.TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.word2.setError(null);

                boolean isInputNotEmpty = s.toString().trim().length() > 0;
                binding.btnCheck.setEnabled(isInputNotEmpty);

                if (wrongAnswersCount == 1) {
                    binding.word2.setHint("Введите пароним");
                    binding.word2.setHintTextColor(Color.GRAY);
                }

                if (binding.word2.getCurrentTextColor() == Color.RED){
                    binding.word2.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnCheck.setOnClickListener(v -> {
            String userAnswer = binding.word2.getText().toString().trim().toLowerCase();
            String correctAnswer = subLevels.get(currentLevelIndex2).getWord2().toLowerCase();
            if(userAnswer.equals(correctAnswer.toLowerCase())) {
                binding.word2.setTextColor(Color.GREEN);
                binding.word2.setEnabled(false);

                binding.btnCheck.setVisibility(View.GONE);
                binding.btnNext2.setVisibility(View.VISIBLE);
            } else {
                wrongAnswersCount++;

                if (wrongAnswersCount >= 2) {
                    binding.word2.setText(correctAnswer);
                    binding.word2.setTextColor(Color.RED);
                    binding.word2.setEnabled(false);
                    binding.word2.setError("Показан правильный ответ");

                    binding.btnCheck.setVisibility(View.GONE);
                    binding.btnNext2.setVisibility(View.VISIBLE);
                } else{
                    binding.word2.setText("");
                    binding.word2.setTextColor(Color.WHITE);

                    binding.word2.setHint("Попробуй ещё");
                    binding.word2.setHintTextColor(Color.RED);
                }
            }
        });

        binding.btnNext2.setOnClickListener(v -> {
            if (currentLevelIndex2 < subLevels.size() -1 ) {
                currentLevelIndex2++;
                displayCurrentSubLevel();

                binding.btnNext2.setVisibility(View.GONE);
                binding.btnCheck.setVisibility(View.VISIBLE);
                binding.word2.setEnabled(true);
                binding.word2.setText("");
                binding.word2.setTextColor(Color.WHITE);
            } else{
                Intent intent = new Intent(Level2Activity.this, FinishActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initSubLevels(){
        subLevels = new ArrayList<>();

        subLevels.add(new SubLevel(R.drawable.adresat_2,"Адресант", "адресат"));
        subLevels.add(new SubLevel(R.drawable.artist_2,"Артистичный –", "артистический"));
        subLevels.add(new SubLevel(R.drawable.boloto_2,"Болотный –", "болотистый"));
        subLevels.add(new SubLevel(R.drawable.budny_2,"Будний –", "будничный"));
        subLevels.add(new SubLevel(R.drawable.vanna_2,"Ванна –", "ванная"));
        subLevels.add(new SubLevel(R.drawable.glina_2,"Глинистый –", "глиняный"));
        subLevels.add(new SubLevel(R.drawable.dlina_2,"Длинный –", "длительный"));
        subLevels.add(new SubLevel(R.drawable.ice_2,"Ледовый –", "ледяной"));
        subLevels.add(new SubLevel(R.drawable.nadel_2,"Надеть –", "одеть"));
        subLevels.add(new SubLevel(R.drawable.neveja_2,"Невежда –", "невежа"));
        subLevels.add(new SubLevel(R.drawable.neterpim_2,"Нетерпимый –", "нестерпимый"));
        subLevels.add(new SubLevel(R.drawable.tsar_2,"Царский –", "царственный"));
        subLevels.add(new SubLevel(R.drawable.apple_2,"Яблоневый –", "Яблочный"));
        subLevels.add(new SubLevel(R.drawable.podpis_2,"Подпись –", "роспись"));
        subLevels.add(new SubLevel(R.drawable.glavniy_2,"Главный –", "заглавный"));
        subLevels.add(new SubLevel(R.drawable.oplata_2,"Оплатить –", "заплатить"));
        subLevels.add(new SubLevel(R.drawable.garantia_2,"Гарантированный –", "Гарантийный"));
        subLevels.add(new SubLevel(R.drawable.difference_2,"Отличать –", "различать"));
        subLevels.add(new SubLevel(R.drawable.effect_2,"Эффективный –", "эффектный"));
        subLevels.add(new SubLevel(R.drawable.neighbour_2,"Соседский –", "соседний"));


        Collections.shuffle(subLevels);
    }

    private void displayCurrentSubLevel() {
        SubLevel current = subLevels.get(currentLevelIndex2);

        wrongAnswersCount = 0;

        binding.viewlevel2.setImageResource(current.getViewlevel2());
        binding.word1.setText(current.getWord1());

        binding.btnCheck.setVisibility(View.VISIBLE);
        binding.btnNext2.setVisibility(View.GONE);

        binding.word2.setEnabled(true);
        binding.word2.setText("");
        binding.word2.setTextColor(Color.WHITE);
        
        binding.btnCheck.setEnabled(false);

        binding.word2.setHint("Введите пароним");
        binding.word2.setHintTextColor(Color.DKGRAY);
    }
}
