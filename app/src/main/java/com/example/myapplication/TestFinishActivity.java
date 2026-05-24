package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.TestFinishActivityBinding;

public class TestFinishActivity extends AppCompatActivity {
    private TestFinishActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestFinishActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackTest.setOnClickListener(v -> {
            finish();
        });

        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 10);
        int score = getIntent().getIntExtra("SCORE", 0);

        String resultText = "Ваш результат " + score + " из " + totalQuestions;
        binding.tvTestResult.setText(resultText);

        if (score >= 0 && score <= 3) {
            binding.tvComment.setText("Нужно повторить материал! 🙁");
            binding.firework.setImageResource(R.drawable.unsmile);
        } else if (score >= 4 && score <= 6) {
            binding.tvComment.setText("Неплохо, но можно лучше! 😐");
            binding.firework.setImageResource(R.drawable.neutral);
        } else if (score >= 7 && score <= 9) {
            binding.tvComment.setText("Хороший результат! Молодец! 🙂");
            binding.firework.setImageResource(R.drawable.smile);
        } else if (score == 10) {
            binding.tvComment.setText("Идеально! Ты знаток! 🥳🔥");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {

                java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
                android.os.Handler mainHandler = new android.os.Handler(android.os.Looper.getMainLooper());

                executor.execute(() -> {
                    try {
                        android.graphics.ImageDecoder.Source source =
                                android.graphics.ImageDecoder.createSource(getResources(), R.drawable.firework);

                        android.graphics.drawable.Drawable drawable = android.graphics.ImageDecoder.decodeDrawable(source);

                        mainHandler.post(() -> {
                            if (drawable instanceof android.graphics.drawable.AnimatedImageDrawable) {
                                binding.firework.setImageDrawable(drawable);
                                ((android.graphics.drawable.AnimatedImageDrawable) drawable).start();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        mainHandler.post(() -> binding.firework.setImageResource(R.drawable.firework));
                    }
                });

            } else {

                binding.firework.setImageResource(R.drawable.party);
            }
        }
        }
}
