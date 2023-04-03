package com.example.testtask.ui;

import static com.example.testtask.MainActivity.FIRST_NAME;
import static com.example.testtask.MainActivity.LAST_NAME;
import static com.example.testtask.MainActivity.REGISTERED;
import static com.example.testtask.MainActivity.editor;
import static com.example.testtask.MainActivity.router;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testtask.R;
import com.example.testtask.cicerone_navigation.Screens;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SetPhotoAndNameFragment extends Fragment implements View.OnClickListener {

    EditText etFirstName, etLastName;
    TextView btnConfirm;
    ImageButton imgBtnSetPhoto;
    ImageView IVPlus;
    Bitmap bitmap;
    Boolean buttonClickable = false;
    CardView cvPhoto;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        assert result.getData() != null;
                        Uri data = result.getData().getData();
                        InputStream stream =
                                requireContext().getContentResolver().openInputStream(data);
                        bitmap = BitmapFactory.decodeStream(stream);
                        imgBtnSetPhoto.setImageBitmap(bitmap);
                        imgBtnSetPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgBtnSetPhoto.setBackground(
                                AppCompatResources.getDrawable(requireContext(), R.drawable.circle));
                        IVPlus.setVisibility(View.INVISIBLE);
                        cvPhoto.setRadius(1000f);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_set_photo_and_name, container, false);

        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        imgBtnSetPhoto = view.findViewById(R.id.imgBtnSetPhoto);
        imgBtnSetPhoto.setOnClickListener(this);

        IVPlus = view.findViewById(R.id.IVPlus);

        cvPhoto = view.findViewById(R.id.cvPhoto);

        etFirstName = view.findViewById(R.id.etFirstName);
        etFirstName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!etFirstName.getText().toString().equals("") && !etLastName.getText().toString().equals("")) {
                    btnConfirm.setBackground(AppCompatResources
                            .getDrawable(requireContext(), R.drawable.rounded_button_green));
                    buttonClickable = true;
                } else {
                    btnConfirm.setBackground(AppCompatResources
                            .getDrawable(requireContext(), R.drawable.rounded_button_gray));
                    buttonClickable = false;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etLastName = view.findViewById(R.id.etLastName);
        etLastName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (etFirstName.getText().toString() != "" && etLastName.getText().toString() != "") {
                    btnConfirm.setBackground(AppCompatResources
                            .getDrawable(requireContext(), R.drawable.rounded_button_green));
                    buttonClickable = true;
                } else {
                    btnConfirm.setBackground(AppCompatResources
                            .getDrawable(requireContext(), R.drawable.rounded_button_gray));
                    buttonClickable = false;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnConfirm: {
                if (buttonClickable) {
                    try {
                        File f = new File(requireContext().getCacheDir(), "profilePhoto");
                        f.createNewFile();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                        byte[] bitmapData = bos.toByteArray();
                        FileOutputStream fos = new FileOutputStream(f);
                        fos.write(bitmapData);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    editor.putString(FIRST_NAME, etFirstName.getText().toString()).apply();
                    editor.putString(LAST_NAME, etLastName.getText().toString()).apply();
                    editor.putBoolean(REGISTERED, true).apply();
                    router.navigateTo(Screens.INSTANCE.mainFragment());
                } else {
                    Toast.makeText(getContext(), "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.imgBtnSetPhoto: {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                someActivityResultLauncher.launch(intent);
            }
        }
    }
}