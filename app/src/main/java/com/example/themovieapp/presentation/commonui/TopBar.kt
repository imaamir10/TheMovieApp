package com.example.themovieapp.presentation.commonui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.themovieapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
        title: String ,
        isBackButtonVisible: Boolean = true,
        onBackClick: () -> Unit = {},
        onActionClick: () -> Unit = {},
) {

    TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
            ),
            title = {
                Text(text = title , color = Color.White)
            },
            navigationIcon = {
                if (isBackButtonVisible) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = "back",
                                tint = Color.White
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = onActionClick) {
                    Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_language),
                            contentDescription = "RTL",
                            tint = Color.White
                    )
                }
            }
    )
}