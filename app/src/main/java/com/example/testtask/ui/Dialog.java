package com.example.testtask.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.testtask.R;

import java.util.Objects;

public class Dialog extends DialogFragment implements View.OnClickListener {

    TextView btnClose, tvText, tvAuthor;

    String[] texts = {
            "Что разум человека может постигнуть и во что он может поверить, того он способен достичь",
            "Стремитесь не к успеху, а к ценностям, которые он дает",
            "Своим успехом я обязана тому, что никогда не оправдывалась и не принимала оправданий от других.",
            "За свою карьеру я пропустил более 9000 бросков, проиграл почти 300 игр. 26 раз мне доверяли сделать финальный победный бросок, и я промахивался. Я терпел поражения снова, и снова, и снова. И именно поэтому я добился успеха.",
            "Сложнее всего начать действовать, все остальное зависит только от упорства.",
            "Надо любить жизнь больше, чем смысл жизни.",
            "Жизнь - это то, что с тобой происходит, пока ты строишь планы.",
            "Логика может привести Вас от пункта А к пункту Б, а воображение — куда угодно.",
            "Через 20 лет вы будете больше разочарованы теми вещами, которые вы не делали, чем теми, которые вы сделали. Так отчальте от тихой пристани. Почувствуйте попутный ветер в вашем парусе. Двигайтесь вперед, действуйте, открывайте!",
            "Возьмите меня на работу, пожалуйста!"
    };

    String[] author = {
            "Наполеон Хилл",
            "Альберт Эйнштейн",
            "Флоренс Найтингейл",
            "Майкл Джордан",
            "Амелия Эрхарт",
            "Федор Достоевский",
            "Джон Леннон",
            "Альберт Эйнштейн",
            "Марк Твен",
            "Антон Логинов"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog, container, false);

        tvAuthor = view.findViewById(R.id.tvAuthor);
        tvText = view.findViewById(R.id.tvText);

        btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

        int randomNumber = (int) (Math.random() * ((9) + 1));
        tvText.setText(texts[randomNumber]);
        tvAuthor.setText(author[randomNumber]);

        return view;
    }

    @Override
    public void onStart() {
        Objects.requireNonNull(getDialog()).getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClose) {
            dismiss();
        }
    }
}
