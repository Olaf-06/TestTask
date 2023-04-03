package com.example.testtask.cicerone_navigation;

import com.github.terrakok.cicerone.Cicerone;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;

public class MyRouter {

    Cicerone<Router> cicerone = Cicerone.create();
    public Router router = cicerone.getRouter();
    public NavigatorHolder navigatorHolder = cicerone.getNavigatorHolder();
}
