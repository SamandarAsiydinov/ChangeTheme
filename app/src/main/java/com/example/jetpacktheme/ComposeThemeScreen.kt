package com.example.jetpacktheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktheme.di.AppTheme
import com.example.jetpacktheme.ui.theme.Purple500
import kotlin.math.acos

@Composable
fun ComposeThemeScreen(
    onItemSelected: (AppTheme) -> Unit
) {
    val menuExpanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Compose Theme"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        menuExpanded.value = !menuExpanded.value
                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More"
                        )
                    }
                    Column(
                        modifier = Modifier.wrapContentSize(Alignment.TopStart)
                    ) {
                        DropdownMenu(
                            expanded = menuExpanded.value,
                            onDismissRequest = {
                                menuExpanded.value = !menuExpanded.value
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .wrapContentSize(Alignment.TopStart)
                        ) {
                            DropdownMenuItem(onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_AUTO.ordinal))
                                menuExpanded.value = !menuExpanded.value
                            }) {
                                Text(
                                    text = "Auto"
                                )
                            }
                            DropdownMenuItem(onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_DAY.ordinal))
                                menuExpanded.value = !menuExpanded.value
                            }) {
                                Text(text = "Light Theme")
                            }
                            DropdownMenuItem(onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_NIGHT.ordinal))
                                menuExpanded.value = !menuExpanded.value
                            }) {
                                Text(text = "Dark Theme")
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }, modifier = Modifier
                    .clip(
                        CircleShape
                    ),
                contentColor = Purple500
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lorem_ip),
                style = MaterialTheme.typography.body1,
                fontSize = 18.sp
            )
        }
    }
}