package com.example.moviedb.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviedb.R

// Set of Material typography styles to start with
/*val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
     Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */


val myFonts = FontFamily(
    Font(R.font.gothica1_black, FontWeight.Light),
    Font(R.font.gothica1_bold, FontWeight.Bold),
    Font(R.font.gothica1_medium, FontWeight.Medium),
    Font(R.font.gothica1_regular, FontWeight.Normal),
    Font(R.font.gothica1_semibold, FontWeight.SemiBold)
)


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = myFonts,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = myFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = myFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    h2 = TextStyle(
        fontFamily = myFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)


