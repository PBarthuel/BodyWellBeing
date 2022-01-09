package com.pbarthuel.bodywellbeing.app.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Modifier
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Bottom app bar example")
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        // TODO change this
                                        kotlin.runCatching {
                                            Firebase.auth.signOut()
                                        }.onSuccess {
                                            finish()
                                        }.onFailure {
                                            Log.d("HomeActivity", "SignOut failure")
                                        }
                                    }
                                ) {
                                    Icon(Icons.Filled.Menu, contentDescription = "")
                                }
                            },
                            elevation = AppBarDefaults.TopAppBarElevation
                        )
                    },
                    bottomBar = {
                        BottomAppBar {

                        }
                    }
                ) {

                }
            }
        }
    }
}