package com.example.worldskillsshop.JC

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            ) {
                Text(it, modifier = Modifier
                    .wrapContentWidth(),
                    color = Color.White
                )
            }
        }
    }
}
@SuppressLint("ComposableNaming")
@Composable
fun CountrySelection(): FontFamily {

    var SpinnerText by remember { mutableStateOf(FontFamily.Default) }
    val countryList = listOf(
        "Cursive",
        "Serif",
        "Default",
        "Monospace",
        "SansSerif",
    )
    val text = remember { mutableStateOf("Default") }
    val isOpen = remember { mutableStateOf(false) }
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
    }
    Box {
        Column {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = "Стиль шрифта: ${text.value}",color = Color.White)},
                modifier = Modifier.fillMaxWidth().padding(all = 5.dp),
                readOnly = true
            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = countryList,
                openCloseOfDropDownList,
                userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }

    when(text.value){
        "Cursive"-> SpinnerText = FontFamily.Cursive
        "Serif"-> SpinnerText = FontFamily.Serif
        "Default"-> SpinnerText = FontFamily.Default
        "Monospace"-> SpinnerText = FontFamily.Monospace
        "SansSerif"-> SpinnerText = FontFamily.SansSerif
    }
    return SpinnerText
}