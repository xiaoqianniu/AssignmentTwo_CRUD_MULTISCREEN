package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.ui.Image

class ProfileScreen(private val email:String) : Screen {

    @Composable
    override fun Content() {

        val screenModel =
            rememberScreenModel() { ProfileScreenModel(LoginRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                topBar = { TopBar() },
                bottomBar = { MyBottomBar() }
            ) {
                Image(
                    url = "https://i.pinimg.com/564x/73/93/0d/73930d5fa877f15da2139d9075d8013e.jpg",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    when (val result = state) {
                        is ProfileScreenModel.State.Init -> Text("")
                        is ProfileScreenModel.State.Loading -> Text("")
                        is ProfileScreenModel.State.Result -> {
                            Text("")
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .aspectRatio(1f)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

                    ) {
                        Image(
                            url = "https://i.pinimg.com/564x/40/9b/94/409b94c14fe4214b5b6397e637c331b9.jpg",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text ="$email" ,
                        modifier = Modifier.padding(top = 20.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        "Your favorite Recipe book",
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )

                }
            }
        }
    }
}