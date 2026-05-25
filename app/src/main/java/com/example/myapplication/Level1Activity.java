package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.Level1ActivityBinding;

import java.util.ArrayList;
import java.util.List;


class LevelData {
    String title, q1, q2, q3;
    String dictionaryText;
    int imageRes;
    boolean[] answers;



    LevelData(String title, String q1, String q2, String q3,String dictionaryText, int imageRes, boolean[] answers){
        this.title = title;
        this.q1 = q1; this.q2 = q2; this.q3 = q3;
        this.dictionaryText = dictionaryText;
        this.imageRes = imageRes; this.answers = answers;

    }
}

public class Level1Activity extends BaseActivity {
    private Level1ActivityBinding binding;
    private List<LevelData> levels = new ArrayList<>();
    private int currentLevelIndex = 0;
    private boolean isLine0Answered = false;
    private boolean isLine1Answered = false;
    private boolean isLine2Answered = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Level1ActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initLevels();

        updateUI();

        binding.yes1.setOnClickListener(v -> checkAnswer(0, true));
        binding.no1.setOnClickListener(v -> checkAnswer(0, false));

        binding.yes2.setOnClickListener(v -> checkAnswer(1, true));
        binding.no2.setOnClickListener(v -> checkAnswer(1, false));

        binding.yes3.setOnClickListener(v -> checkAnswer(2, true));
        binding.no3.setOnClickListener(v -> checkAnswer(2, false));

        binding.btnNext.setOnClickListener(v -> {
            if (currentLevelIndex < levels.size() - 1) {
                currentLevelIndex++;

                isLine0Answered = false;
                isLine1Answered = false;
                isLine2Answered = false;
                binding.btnNext.setEnabled(false);

                updateUI();
            } else {
                Intent intent = new Intent(Level1Activity.this, FinishActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initLevels() {
        levels.add(new LevelData(
                "Адресант – адресат",
                "Утром адресат получил заказное письмо.",
                "Узнать о местонахождении ожидаемой посылки адресант может по трек-номеру отправления.",
                "При отправке письма адресант должен указать почтовый индекс.",
                "Адресант – отправитель какой-либо информации через интернет, факс или по почте.\n" +
                        "\n" +
                        "Адресат – получатель какой-либо информации, почтового отправления.\n",
                R.drawable.adresat_1,
                new boolean[]{true, true, false}));
        levels.add(new LevelData(
                "Артистический – артистичный",
                "В артистической семье Никиты Михалкова растёт ещё одна звёздочка.",
                "Его артистичная судьба неразрывно связана с цирком.",
                "Победителями в номинации «Артистичное исполнение» были признаны ребята из 5 «А» класса.",
                "Артистический – 1. Относящийся к артисту; предназначенный для артиста.\n" +
                        "2. Свойственный артисту; такой, как у артиста.\n" +
                        "3. Выполненный с большим искусством, с высоким мастерством, виртуозный.\n" +
                        "\n" +
                        "Артистичный – отличающийся артистизмом, виртуозный.\n",
                R.drawable.artist_1,
                new boolean[]{true, false, true}));
       levels.add(new LevelData(
                "Болотистый – болотный",
                "Незадачливого грибника два дня искали в болотистой местности.",
                "Папины болотные сапоги мне очень велики.",
                "Маша достала из шкафа куртку болотистого цвета.",
                "Болотистый – изобилующий болотами; топкий, вязкий.\n" +
                        "\n" +
                        "Болотный – 1. Относящийся к болоту; свойственный болоту.\n" +
                        "2. Предназначенный для работ на болоте, передвижения по болоту.\n" +
                        "3. Живущий, произрастающий на болоте.\n" +
                        "4. Напоминающий цвет болота.\n",
                R.drawable.boloto_1,
                new boolean[]{true, true, false}));
        levels.add(new LevelData(
                "Будний – будничный",
                "Новогодние каникулы закончились, и люди приступили к своим будним делам.",
                "Жизнь в глухой деревушке представлялась ей будничной.",
                "В будние дни я занята, поэтому давай сходим в кино в выходные.",
                "Будний – не праздничный, рабочий (о времени), относящийся к будням.\n" +
                        "\n" +
                        "Будничный – 1. Не праздничный, рабочий (о времени).\n" +
                        "1а. Предназначенный для будней (одежда, туфли).\n" +
                        "1б. Повседневный, обыденный (работа, занятие).\n" +
                        "2. Однообразный; беспросветный, безрадостный (жизнь, быт, голос).\n",
                R.drawable.budny_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Ванна – ванная",
                "Педиатр порекомендовала ребёнку принимать ванную с ромашкой.",
                "Маша вышла из ванны с полотенцем на голове.",
                "Папа купил новую настенную полочку в ванную.",
                "Ванна – 1. Большой сосуд для купания, мытья.\n" +
                        "2. Мытьё или лечебная процедура в таком сосуде.\n" +
                        "\n" +
                        "Ванная – комната, в которой находится ванна и принимаются ванны.\n",
                R.drawable.vanna_1,
                new boolean[]{false, false, true}));
        levels.add(new LevelData(
                "Глинистый – глиняный",
                "Глинистая почва с трудом поддаётся обработке, и многие культуры на ней не растут.",
                "В гончарной мастерской ученики сами лепили и расписывали глиняные игрушки.",
                "Дедушка любит есть картошку, запечённую в глинистом горшочке.",
                "Глинистый – содержащий глину, изобилующий глиной (обрыв, почва).\n" +
                        "\n" +
                        "Глиняный – 1. Сделанный из глины (посуда, игрушка).\n" +
                        "2. Состоящий из глины, покрытый глиной (карьер, берег).\n",
                R.drawable.glina_1,
                new boolean[]{true, true, false}));
        levels.add(new LevelData(
                "Длинный – длительный",
                "После болезни пациенту предстоит длинное лечение.",
                "Свадебное платье с длительным шлейфом невероятно красиво.",
                "Из подъезда вышел длинный юноша в сером пальто.",
                "Длинный – 1. Имеющий большую длину, протяжённость.\n" +
                        "2. Высокий ростом (о человеке).\n" +
                        "3. Долго продолжающийся, продолжительный.\n" +
                        "\n" +
                        "Длительный – долго продолжающийся; долгий, продолжительный.\n",
                R.drawable.dlina_1,
                new boolean[]{false, false, true}));
        levels.add(new LevelData(
                "Ледовый – ледяной",
                "В детстве ледовые сосульки казались вкусными.",
                "Жители Новосибирска ждут открытия нового ледового дворца спорта.",
                "На ледяной дороге машину занесло в фонарный столб.",
                "Ледовый – 1. Состоящий изо льда; ледяной; покрытый, скованный льдами.\n" +
                        "2. Находящийся, расположенный на льду.\n" +
                        "3. Происходящий во льдах; относящийся к каким-либо действиям во льдах.\n" +
                        "4. Предназначенный для работы во льдах; служащий для обработки льда.\n" +
                        "\n" +
                        "Ледяной – 1. Относящийся ко льду, состоящий изо льда; сделанный изо льда.\n" +
                        "2. Покрытый льдом; обледенелый.\n" +
                        "3. Очень холодный, холодный как лёд.\n" +
                        "4. Холодный, уничтожающий; холодно-равнодушный, безучастный, невозмутимый.\n" +
                        "4а. Хладнокровный, крайне сдержанный в проявлении чувств; лишённый живого чувства, рассудочный.\n",
                R.drawable.ice_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Надеть – одеть",
                "Бабушка надела чистую наволочку на подушку.",
                "Мама одела тёплые перчатки на ребёнка.",
                "Утром густой туман одел землю.",
                "Надеть (что?) – 1. Натянуть, надвинуть (одежду, обувь, чехол и т. п.), покрывая, облекая кого-что-либо.\n" +
                        "2. Укрепить предмет на чём-либо, прикрепить на что-либо. 3. Насадить, продевая или накалывая.\n" +
                        "\n" +
                        "Одеть (кого/что?) – 1. Облечь кого-либо в какую-либо одежду.\n" +
                        "1а. Нарядить кем-либо.\n" +
                        "1б. Снабдить, обеспечить одеждой.\n" +
                        "2. Покрыть, укрыть кого-либо чем-либо для тепла.\n" +
                        "3. Покрыть, окутать (о тумане, мраке и т. п.).\n" +
                        "3а. Покрыть какую-либо поверхность каким-либо материалом, облицевать.\n" +
                        "\n" +
                        "У глаголов разные антонимы: надеть – снять, одеть – раздеть.\n" +
                        "!!! Надеть (что?) одежду, одеть (кого?) Надежду.\n",
                R.drawable.nadel_1,
                new boolean[]{true, false, true}));
        levels.add(new LevelData(
                "Невежа – невежда",
                "Только невежа не уступает место пожилым людям место в автобусе.",
                "Олег – полный невежа в геометрии, зато он отлично разбирается в химии.",
                "Парень понял, что наступил мне на ногу, но даже не извинился, удивительный невежа.",
                "Невежа – грубый, невоспитанный человек.\n" +
                        "\n" +
                        "Невежда – необразованный, несведущий человек; малосведущий в какой-либо области знаний.\n" +
                        "\n" +
                        "!!! Невежа – невежливый, невежда – не ведает (не знает).\n",
                R.drawable.neveja_1,
                new boolean[]{true, false, true}));
        levels.add(new LevelData(
                "Нестерпимый – нетерпимый",
                "На улице стояла нетерпимая жара, и мы постоянно пили воду.",
                "Учитель истории был нетерпимым к опозданиям, и очень ругался, если ученик заходил в класс после звонка.",
                "Мальчуган чувствовал такой нестерпимый стыд за своё поведение, что готов был сквозь землю провалиться.",
                "Нестерпимый – такой, который трудно, невозможно стерпеть, перенести, невыносимый; очень сильный по степени своего проявления.\n" +
                        "\n" +
                        "Нетерпимый – 1. Такой, который нельзя терпеть, с которым нельзя мириться, недопустимый.\n" +
                        "2. (Обычно в составе сказуемого) Такой, который по складу своего характера лишён терпимости, " +
                        "не может мириться с кем-чем-либо, признавать кого-что-либо; не считающийся с чужим мнением.\n",
                R.drawable.neterpim_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Царский – царственный",
                "При Иване Грозном царские приёмы и торжественные застолья стали частыми.",
                "Царственный двуглавый орёл украшает Петровские ворота Петропавловской крепости.",
                "Глубока и могуча царская река Обь.",
                "Царский – 1. Относящийся к царю, монарху; принадлежащий царю.\n" +
                        "2. Присущий, свойственный царю, царице.\n" +
                        "3. Относящийся к власти царя, к монархии.\n" +
                        "4. Роскошный, великолепный; исключительный по размаху.\n" +
                        "\n" +
                        "Царственный – 1. Величественный, величавый; выражающий величественность, величавость.\n" +
                        "2. Относящийся к царю, монарху; принадлежащий царю.\n",
                R.drawable.tsar_1,
                new boolean[]{true, true, false}));
        levels.add(new LevelData(
                "Яблочный – яблоневый",
                "Цветущий яблочный сад – завораживающее зрелище.",
                "В нашей пекарне продают вкусные слойки с яблочным вареньем.",
                "С наступлением весеннего тепла садоводы белят яблоневые стволы.",
                "Яблочный – приготовленный из яблок или с яблоками.\n" +
                        "\n" +
                        "Яблоневый – относящийся к яблони, состоящий из яблонь.\n",
                R.drawable.apple_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Подпись – роспись",
                "Сегодня мы прошли инструктаж по технике безопасности в бассейне и поставили свою роспись в журнале регистрации.",
                "Керамическая ваза была украшена яркой городецкой росписью.",
                "Стихотворение С. Есенина «Берёза» было опубликовано в журнале для детей «Мирок» за подписью «Аристон».",
                "Подпись – 1. Надпись под чем-либо, на чём-либо.\n" +
                        "2. Собственноручно написанная фамилия под чем-либо в подтверждение своего авторства, ознакомления или согласия с чем-либо.\n" +
                        "\n" +
                        "Роспись – 1. Декоративная живопись на стенах, потолках зданий и предметах быта.\n" +
                        "2. Письменный перечень, список чего-либо.",
                R.drawable.podpis_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Главный – заглавный",
                "Заглавная буква в английском языке используется гораздо чаще, чем в русском.",
                "В детстве я любил перечитывать сборник Н. Носова «Мишкина каша», особенно его главный рассказ.",
                "Заглавную роль в сериале «Есенин» сыграл Сергей Безруков.",
                "Главный – 1. Самый важный, основной, наиболее существенный среди других.\n" +
                        "2. Находящийся, расположенный в центре, в середине чего-либо; самый важный.\n" +
                        "3. Старший по положению, возглавляющий кого-что-либо.\n" +
                        "\n" +
                        "Заглавный – относящийся к заглавию, содержащий заглавие, являющийся заглавием, названием чего-либо.\n",
                R.drawable.glavniy_1,
                new boolean[]{true, false, true}));
        levels.add(new LevelData(
                "Выплатить – заплатить – оплатить",
                "Мальчик оплатил за проезд картой школьника.",
                "Вася выплатил автокредит досрочно.",
                "Мне нужно заплатить за телефон до конца месяца.",
                "Выплатить (что?) – выдать плату; полностью или частями отдать деньги (зарплату, премию, долг, кредит, налог).\n" +
                        "\n" +
                        "Заплатить (за что?) – 1. Отдать плату, деньги за что-либо (за квартиру, за телефон, за проезд, за покупку).\n" +
                        "2. Поступить каким-либо образом в ответ на что-либо, совершить что-либо в связи с чем-либо, проявляя своё отношение к чему-либо.\n" +
                        "\n" +
                        "Оплатить (что?) – отдать деньги за что-либо, в возмещение чего-либо; отдать какую-либо сумму денег в погашение чего-либо (расходы, работу, счёт, проезд, экскурсию).\n",
                R.drawable.oplata_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Гарантийный – гарантированный",
                "Продавец пояснил, что в случае поломки телефона я имею право на его бесплатный гарантийный ремонт в течение 12 месяцев.",
                "Современные сорта томатов способны давать постоянный гарантированный урожай.",
                "Согласно гарантированному письму, компьютерная фирма обязуется оплатить доставку товара в следующем месяце.",
                "Гарантийный – относящийся к гарантии, ручательству, содержащий гарантию; служащий гарантией (ремонт, письмо, обязательство).\n" +
                        "\n" +
                        "Гарантированный – 1. Обусловленный, поддержанный законом, обязательствами официальных лиц, учреждений. 2. Обеспеченный (доход, зарплата, работа, урожай).\n",
                R.drawable.garantia_1,
                new boolean[]{true, true, false}));
        levels.add(new LevelData(
                "Отличать – различать",
                "Кошки хорошо различают движущиеся предметы на расстоянии.",
                "Сегодня на уроке музыки мы учились отличать звучание скрипки.",
                "Он совсем не различает съедобные грибы и ядовитые.",
                "Отличать – 1. (кого/что от кого-чего?) Распознавать нечто среди прочего.\n" +
                        "2. Награждать, выделять (устар.)\n" +
                        "\n" +
                        "Различать (кого/что?) – распознавать зрением или другими органами чувств.\n",
                R.drawable.difference_1,
                new boolean[]{true, false, true}));
        levels.add(new LevelData(
                "Эффективный – эффектный",
                "Гигиена рук – самый доступный и эффектный способ защититься от инфекционных заболеваний.",
                "На мероприятии Ольга была в эффектном чёрном платье.",
                "Для эффективного усвоения новых знаний нужна задача, которую интересно будет решать.",
                "Эффективный – 1. Приводящий к нужным результатам, действенный.\n" +
                        "2. Дающий наибольший эффект, результат; более совершенный, производительный.\n" +
                        "\n" +
                        "Эффектный – производящий сильное впечатление, эффект; рассчитанный на то, чтобы произвести эффект.\n",
                R.drawable.effect_1,
                new boolean[]{false, true, true}));
        levels.add(new LevelData(
                "Соседний – соседский",
                "В соседней школе прошли соревнования по мини-футболу среди учащихся 7-9 классов.",
                "Соседский кот каждый день выходит на крыльцо погреться на солнышке.",
                "В соседском многоквартирном доме открыли булочную.",
                "Соседний – расположенный вблизи, рядом с кем-чем-либо, по соседству.\n" +
                        "\n" +
                        "Соседский – относящийся к соседу, принадлежащий ему.\n",
                R.drawable.neighbour_1,
                new boolean[]{true, true, false}));


    }


    private void updateUI() {
        LevelData level = levels.get(currentLevelIndex);

        binding.titleTextView.setText(level.title);
        binding.question1.setText(level.q1);
        binding.question2.setText(level.q2);
        binding.question3.setText(level.q3);
        binding.viewlevel1.setImageResource(level.imageRes);


        resetRow(binding.yes1, binding.no1);
        resetRow(binding.yes2, binding.no2);
        resetRow(binding.yes3, binding.no3);
    }

    private void checkAnswer(int questionIndex, boolean userChoice) {
        LevelData currentLevel = levels.get(currentLevelIndex);
        boolean correctAnswer = currentLevel.answers[questionIndex];

        switch (questionIndex) {
            case 0:
                applyNewImage(binding.yes1, binding.no1, userChoice, correctAnswer);
                isLine0Answered = true;
                break;
            case 1:
                applyNewImage(binding.yes2, binding.no2, userChoice, correctAnswer);
                isLine1Answered = true;
                break;
            case 2:
                applyNewImage(binding.yes3, binding.no3, userChoice, correctAnswer);
                isLine2Answered = true;
                break;
        }

        boolean allAnswered = isLine0Answered && isLine1Answered && isLine2Answered;

        binding.btnNext.setEnabled(allAnswered);
    }

    private void applyNewImage(ImageView yes, ImageView no, boolean userChoice, boolean correctAnswer) {
        if(userChoice) {
            if(correctAnswer){
                yes.setImageResource(R.drawable.yes_green_min);
            } else{
                yes.setImageResource(R.drawable.yes_red_min);
            }
            no.setImageResource(R.drawable.no_min);
        } else {
            if(!correctAnswer) {
                no.setImageResource(R.drawable.no_green_min);
            } else {
                no.setImageResource(R.drawable.no_red_min);
            }
            yes.setImageResource(R.drawable.yes_min);
        }

        yes.setEnabled(false);
        no.setEnabled(false);

    }
    private void resetRow(ImageView yes, ImageView no) {
        yes.setImageResource(R.drawable.yes_min);
        no.setImageResource(R.drawable.no_min);

        yes.setEnabled(true);
        no.setEnabled(true);


        binding.btnDictionary.setOnClickListener(v -> {
            LevelData current = levels.get(currentLevelIndex);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_dictionary, null);

            TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
            TextView dialogText = dialogView.findViewById(R.id.dialogText);

            dialogTitle.setText(current.title);
            dialogText.setText(current.dictionaryText);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .create();

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.show();
        });
    }
}





