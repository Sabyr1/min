package com.example.myapplication.data.repository.pages.Nav

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Profile(){
    Text (
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .background(Color.White),
        text = "Profile",
        textAlign = TextAlign.Center
    )
}