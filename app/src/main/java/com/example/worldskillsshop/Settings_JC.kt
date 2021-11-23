package com.example.worldskillsshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldskillsshop.JC.*
import com.example.worldskillsshop.ui.theme.Gray_14
import com.example.worldskillsshop.ui.theme.Gray_20
import com.example.worldskillsshop.ui.theme.WorldskillsShopTheme

class Settings_JC : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldskillsShopTheme {

                Greeting()

            }
        }
    }
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val checkedState = remember { mutableStateOf(true) }
    val textColor = remember { mutableStateOf(Color.Unspecified) }
    var sliderPosition by remember{mutableStateOf(14f)}
    var SpinnerText:FontFamily by remember{mutableStateOf(FontFamily.Default)}



    WorldskillsShopTheme {
        LaunchedEffect(Unit) { scrollState.animateScrollTo(10000) }

        Column {
            ToolBar("Настройки", SpinnerText,sliderPosition+2,context)

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .background(color = Gray_14)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){


                Row(modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(color = Gray_20)
                ) {

                    Text_Settings(text = "Уведомления", size = sliderPosition, family = SpinnerText,Modifier.padding(start = 15.dp,top = 23.dp,end = 0.dp,bottom = 20.dp))

                    Column {
                        Switch(checked = checkedState.value, onCheckedChange = {
                            checkedState.value = it
                            if(checkedState.value) textColor.value = Color(0xFF292828)
                            else textColor.value = Color.Unspecified
                        }, modifier = Modifier.padding(start = 5.dp,bottom = 20.dp,top = 20.dp))
                    }
                }
                Column(modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(color = Gray_20)) {

                    Text_Settings(text = "Размер шрифта: $sliderPosition", size = sliderPosition, family = SpinnerText,Modifier.padding(start = 15.dp,top = 20.dp,end = 0.dp,bottom = 2.dp).fillMaxWidth())

                    Slider(value = sliderPosition,
                        onValueChange = {
                            sliderPosition = it
                        },
                        valueRange = 10f..20f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF000000),
                            activeTrackColor = Color(0xFF2C2B2B),
                            inactiveTrackColor = Color.Gray
                        ),
                        steps = 8,
                        modifier = Modifier
                            .padding(start = 15.dp,bottom = 2.dp,top = 2.dp)
                    )

                    SpinnerText =  CountrySelection()
                }
                Row(modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(color = Gray_20)
                ){

                    Text_Settings_Color(text = "Сменить аккаунт", size = sliderPosition,
                        family = SpinnerText,
                        Modifier.padding(start = 15.dp,top = 20.dp,end = 0.dp,bottom = 20.dp).fillMaxWidth(), Color.Red)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorldskillsShopTheme {

        Greeting()

    }
}