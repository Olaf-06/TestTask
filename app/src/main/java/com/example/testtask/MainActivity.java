package com.example.testtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.testtask.cicerone_navigation.MyRouter;
import com.example.testtask.cicerone_navigation.Screens;
import com.github.terrakok.cicerone.Navigator;
import com.github.terrakok.cicerone.Router;
import com.github.terrakok.cicerone.androidx.AppNavigator;

public class MainActivity extends AppCompatActivity {

    public static final String SEX = "sex";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHOTO = "photo";
    public static final String REGISTERED = "registered";

    static public SharedPreferences sharedPreferences;
    static public SharedPreferences.Editor editor;

    static public Router router;
    MyRouter myRouter;
    Navigator navigator = new AppNavigator(this, R.id.container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_TestTask);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        myRouter = new MyRouter();
        router = myRouter.router;
        if (sharedPreferences.getBoolean(REGISTERED, false)) {
            router.newRootScreen(Screens.INSTANCE.mainFragment());
        } else {
            router.newRootScreen(Screens.INSTANCE.chooseSexFragment());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        myRouter.navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myRouter.navigatorHolder.removeNavigator();
    }
}