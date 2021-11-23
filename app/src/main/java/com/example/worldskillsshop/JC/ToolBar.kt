package com.example.worldskillsshop.JC

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldskillsshop.R
import com.example.worldskillsshop.openHome
import com.example.worldskillsshop.ui.theme.Gray_14

@Composable
fun ToolBar(title:String,family: FontFamily,size:Float,context: Context) {
    TopAppBar(
        title = { Text(text = title,color = Color.White,fontSize = size.sp, fontFamily = family)},
        backgroundColor = Gray_14,
        navigationIcon = {

            IconButton(onClick = {
                openHome(context)

            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24) ,
                    contentDescription = "back", tint = Color.White,modifier = Modifier.padding(start = 5.dp))
            }
        }
    )
}