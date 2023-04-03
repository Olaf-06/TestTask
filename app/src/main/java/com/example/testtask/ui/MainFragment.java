package com.example.testtask.ui;

import static com.example.testtask.MainActivity.FIRST_NAME;
import static com.example.testtask.MainActivity.LAST_NAME;
import static com.example.testtask.MainActivity.sharedPreferences;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testtask.R;

import java.io.File;

public class MainFragment extends Fragment {

    ImageView IVProfilePhoto;
    TextView TVProfileName;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        IVProfilePhoto = view.findViewById(R.id.IVProfilePhoto);

        File imgFile = new File(requireContext().getCacheDir(), "profilePhoto");

        if (imgFile.exists()) {

            Bitmap imgBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            IVProfilePhoto.setImageBitmap(imgBitmap);
        }

        TVProfileName = view.findViewById(R.id.TVProfileName);
        TVProfileName.setText(sharedPreferences.getString(FIRST_NAME, "") + " "
                + sharedPreferences.getString(LAST_NAME, ""));

        Dialog dialog = new Dialog();
        dialog.show(getChildFragmentManager(),"dialog");
        return view;
    }
}