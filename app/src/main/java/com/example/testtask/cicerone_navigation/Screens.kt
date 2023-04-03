package com.example.testtask.cicerone_navigation

import com.example.testtask.ui.ChooseSexFragment
import com.example.testtask.ui.MainFragment
import com.example.testtask.ui.SetPhotoAndNameFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun chooseSexFragment() = FragmentScreen { ChooseSexFragment() }
    fun setPhotoAndNameFragment() = FragmentScreen { SetPhotoAndNameFragment() }
    fun mainFragment() = FragmentScreen { MainFragment() }
}