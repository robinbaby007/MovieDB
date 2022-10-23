package com.example.moviedb.utils

import com.example.moviedb.R

sealed class HomeBottomNavigation(val title:String,val icon:Int,val route:String){

    object Home:HomeBottomNavigation("Home", R.drawable.ic_home,"home")
    object Film:HomeBottomNavigation("Film", R.drawable.ic_filim,"film")
    object TV:HomeBottomNavigation("TV", R.drawable.ic_tv,"tv")


}