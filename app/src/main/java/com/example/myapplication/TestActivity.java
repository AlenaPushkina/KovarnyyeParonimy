package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.TestActivityBinding;

import java.util.ArrayList;
import java.util.Collections;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;


public class TestActivity extends BaseActivity {
    private TestActivityBinding binding;
    private ArrayList<TestQuestion> testQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int selectedOption;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initTestQuestoins();

        displayQuestion();

        binding.btnOption1.setOnClickListener(v -> selectedOption(1));
        binding.btnOption2.setOnClickListener(v -> selectedOption(2));

        binding.btnNextTest.setOnClickListener(v -> {
            checkAnswer();

            currentQuestionIndex++;

            if (currentQuestionIndex < testQuestions.size()) {
                displayQuestion();
            } else {
                Intent intent = new Intent(TestActivity.this, TestFinishActivity.class );
                intent.putExtra("TOTAL_QUESTIONS", testQuestions.size());
                intent.putExtra("SCORE", score);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initTestQuestoins() {
        ArrayList<TestQuestion> allQuestions = new ArrayList<>();

        allQuestions.add(new TestQuestion("При отправке письма ... должен указать почтовый индекс.", "Адресант", "Адресат",1));
        allQuestions.add(new TestQuestion("В ... семье Никиты Михалкова растёт ещё одна звёздочка.", "Артистической", "Артистичной",1));
        allQuestions.add(new TestQuestion("Папины ... сапоги мне очень велики.", "Болотистые", "Болотные",2));
        allQuestions.add(new TestQuestion("Жизнь в глухой деревушке представлялась ей ... .", "Будной", "Будничной",2));
        allQuestions.add(new TestQuestion("Папа купил новую настенную полочку в ... .", "Ванну", "Ванную",2));
        allQuestions.add(new TestQuestion("В гончарной мастерской ученики сами лепили и расписывали ... игрушки.", "Глиняные", "Глинистые",1));
        allQuestions.add(new TestQuestion("После болезни пациенту предстоит ... лечение.", "Длительное", "Длинное",1));
        allQuestions.add(new TestQuestion("Жители Новосибирска ждут открытия нового ... дворца спорта.", "Ледяного", "Ледового",2));
        allQuestions.add(new TestQuestion("Мама ... тёплые перчатки на ребёнка.", "Надела", "Одела",1));
        allQuestions.add(new TestQuestion("Только ... не уступает место пожилым людям место в автобусе.", "Невежда", "Невежа",2));
        allQuestions.add(new TestQuestion("На улице стояла ... жара, и мы постоянно пили воду.", "Нестерпимая", "Нетерпимая",1));
        allQuestions.add(new TestQuestion("При Иване Грозном ... приёмы и торжественные застолья стали частыми.", "Царственные", "Царские",2));
        allQuestions.add(new TestQuestion("В нашей пекарне продают вкусные слойки с ... вареньем.", "Яблоневым", "Яблочным",2));
        allQuestions.add(new TestQuestion("Сегодня мы прошли инструктаж по технике безопасности в бассейне и поставили свою ... в журнале регистрации.", "Подпись", "роспись",1));
        allQuestions.add(new TestQuestion("... роль в сериале «Есенин» сыграл Сергей Безруков.", "Главную", "Заглавную",2));
        allQuestions.add(new TestQuestion("Мне нужно ... за телефон до конца месяца.", "Оплатить", "Заплатить",2));
        allQuestions.add(new TestQuestion("Современные сорта томатов способны давать постоянный ... урожай.", "Гарантированный", "Гарантийный",1));
        allQuestions.add(new TestQuestion("Кошки хорошо ... движущиеся предметы на расстоянии.", "Различают", "Отличают",1));
        allQuestions.add(new TestQuestion("На мероприятии Ольга была в ... чёрном платье.", "Эффективном", "Эффектном",2));
        allQuestions.add(new TestQuestion("... кот каждый день выходит на крыльцо погреться на солнышке.", "Соседский", "Соседний",1));

        Collections.shuffle(allQuestions);

        testQuestions = new ArrayList<>();
        for (int i = 0; i < 10 && i < allQuestions.size(); i++) {
            testQuestions.add(allQuestions.get(i));
        }
    }

    private void displayQuestion() {
        TestQuestion current = testQuestions.get(currentQuestionIndex);

        binding.tvSentence.setText(current.getSentence());
        binding.btnOption1.setText(current.getOption1());
        binding.btnOption2.setText(current.getOption2());


        binding.btnOption1.setBackgroundColor(Color.LTGRAY);
        binding.btnOption2.setBackgroundColor(Color.LTGRAY);

        binding.btnOption1.setEnabled(true);
        binding.btnOption2.setEnabled(true);

        binding.btnNextTest.setVisibility(View.GONE);
        selectedOption = 0;
    }

private void selectedOption(int optionNumber) {
        selectedOption = optionNumber;

        if (optionNumber == 1) {
            binding.btnOption1.setBackgroundColor(Color.WHITE);
            binding.btnOption2.setBackgroundColor(Color.DKGRAY);
        } else {
            binding.btnOption2.setBackgroundColor(Color.WHITE);
            binding.btnOption1.setBackgroundColor(Color.DKGRAY);
        }
        binding.btnNextTest.setVisibility(View.VISIBLE);
    }

    private void checkAnswer() {
        TestQuestion current = testQuestions.get(currentQuestionIndex);
        if (selectedOption == current.getCorrectOption()) {
            score++;
        }
    }
}
