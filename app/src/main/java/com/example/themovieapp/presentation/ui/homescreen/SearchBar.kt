package com.example.themovieapp.presentation.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themovieapp.R


@Composable
fun SearchBar(
        modifier: Modifier,
        text: String,
        onTextChange: (String) -> Unit = {},
        onSearchClicked: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
            modifier = modifier,
            shadowElevation = 0.dp,
            tonalElevation = 5.dp,
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
    ) {
        TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),

                value = text,
                textStyle = TextStyle(
                        color = Color.Black
                ),
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                            text = "Search Movies",
                            color = Color.Gray
                    )
                },
                leadingIcon = {
                    Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search icon",
                            tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedPlaceholderColor = Color.White,
                        unfocusedPlaceholderColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,


                        ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            onSearchClicked(text)
                        }
                )

        )
    }
}


@Preview()
@Composable
private fun SearchbarPreview() {
    SearchBar(
            modifier = Modifier,
            text = "",
            onTextChange = {},
            onSearchClicked = {}
    )
}