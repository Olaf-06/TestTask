package com.example.testtask.ui;

import static com.example.testtask.MainActivity.SEX;
import static com.example.testtask.MainActivity.editor;
import static com.example.testtask.MainActivity.router;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testtask.R;
import com.example.testtask.cicerone_navigation.Screens;

public class ChooseSexFragment extends Fragment implements View.OnClickListener {

    TextView btnMale, btnFemale, btnNext;
    boolean sex, btnNextClicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_sex, container, false);

        btnMale = view.findViewById(R.id.btnMale);
        btnMale.setOnClickListener(this);

        btnFemale = view.findViewById(R.id.btnFemale);
        btnFemale.setOnClickListener(this);

        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        
        switch (view.getId()) {
            case R.id.btnNext: {
                if (btnNextClicked) {
                    editor.putBoolean(SEX, sex);
                    btnNextClicked = false;
                    router.navigateTo(Screens.INSTANCE.setPhotoAndNameFragment());
                } else {
                    Toast.makeText(getContext(), "Choose your gender", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnMale: {
                btnNextClicked = true;
                sex = true;
                btnMale.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_green));
                btnNext.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_green));
                btnFemale.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_dark));
                break;
            }
            case R.id.btnFemale: {
                btnNextClicked = true;
                sex = false;
                btnFemale.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_green));
                btnNext.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_green));
                btnMale.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_button_dark));
                break;
            }
        }
    }
}