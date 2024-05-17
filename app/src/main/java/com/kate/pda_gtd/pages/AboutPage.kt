package com.kate.pda_gtd.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kate.pda_gtd.R
import com.kate.pda_gtd.ui.theme.Pink80
import com.kate.pda_gtd.ui.theme.Purple80
import com.kate.pda_gtd.ui.theme.PurpleGrey40

class AboutPage {
    @Composable
    fun AboutPageContent(
    ) {
        val uriHandler = LocalUriHandler.current
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Icon(
                    painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "GTD",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 48.dp,
                            bottom = 4.dp
                        ),
                    colors = CardDefaults.cardColors(containerColor = Purple80)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "GTD creator: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        Text(
                            text = "Kateryna Dmytryienko",
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Purple80)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sources: ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Card(
                            modifier = Modifier
                                .clickable { }
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_gitlab),
                                contentDescription = "GitLab logo",
                                modifier = Modifier
                                    .padding(22.dp)
                                    .clickable {
                                        uriHandler.openUri("https://gitlab.fel.cvut.cz/dmytrkat/pda_gtd")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}