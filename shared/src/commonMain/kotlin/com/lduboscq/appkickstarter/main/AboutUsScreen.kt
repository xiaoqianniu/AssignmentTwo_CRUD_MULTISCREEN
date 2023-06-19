package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.appkickstarter.shared.SharedRes
import com.lduboscq.appkickstarter.ui.Image

class AboutUsScreen : Screen {
    @Composable
    override fun Content() {
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
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "About Us",
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    ContactInfoCard(
                        "John Doe",
                        "john.doe@example.com",
                        "+1 234-567-890",
                        "123 Street, City, Country"
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                                "Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                                "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                                "dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
                                "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
                                "sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(
                        text = "Website: www.example.com",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.clickable(onClick = {}),
                       color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun ContactInfoCard(name: String, email: String, phone: String, address: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 10.dp,

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Name: $name",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Phone: $phone",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "Address: $address",
                style = MaterialTheme.typography.body1
            )
        }
    }
}




