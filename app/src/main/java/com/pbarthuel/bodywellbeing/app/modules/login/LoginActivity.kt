package com.pbarthuel.bodywellbeing.app.modules.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null){
            // TODO lancer l'activity après connexion (ici le user est déjà log)
        }

        setContent {
            BodyWellBeingTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Button(modifier = Modifier.fillMaxWidth().align(CenterHorizontally).padding(24.dp), onClick = {

                    }) {
                        Text(text = "Login activity")
                    }
                }
            }
        }
    }

    //TODO methode de creation de user a mettre dans le viewModel
    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when (task.isSuccessful) {
                    true -> {
                        Log.d("LoginActivity", "createUserWithEmail:success")
                        // TODO ici lancer l'activity après connexion (le user vient de créer un compte et de se log)
                    }
                    else -> {
                        Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        // TODO ici la création a fail
                    }
                }
        }
    }

    //TODO methode de log de user a mettre dans le viewModel
    fun logUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    // TODO ici lancer l'activity après connexion (le user vient de se log)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    // TODO ici le log a fail
                }
            }
    }
}