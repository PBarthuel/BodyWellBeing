package com.pbarthuel.bodywellbeing.app.modules.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Login activity", modifier = Modifier.align(CenterHorizontally))
                }
            }
        }
    }
}