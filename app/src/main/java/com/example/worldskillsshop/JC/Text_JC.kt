package com.example.worldskillsshop.JC

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Text_Settings(text:String,size:Float,family:String,
                  start:Int,
                  top:Int,
                  bottom:Int,
                  end:Int) {


    Text(text = text,
        modifier = Modifier
            .padding(start = start.dp,bottom = bottom.dp,top = top.dp,end = end.dp),
        color = Color.White,
        fontSize = size.sp,
        fontFamily = null)
}