package com.example.worldskillsshop.JC

import android.annotation.SuppressLint
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun Text_Settings(text:String, size:Float, family:FontFamily, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {

    Text(text = text,
        modifier,
        color = Color.White,
        fontSize = size.sp,
        fontFamily = family
    )
}

@Composable
fun Text_Settings_Color(text:String, size:Float, family:FontFamily, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,color: Color) {

    Text(text = text,
        modifier,
        color = color,
        fontSize = size.sp,
        fontFamily = family
    )
}