package com.pbarthuel.bodywellbeing.app.modules.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.modules.home.HomeActivity
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val showGoogleSignInActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)
                    Log.d("GoogleActivity", "firebaseAuthWithGoogle:" + account.id)
                    account.idToken?.let {
                        firebaseAuthWithGoogle(it)
                    } ?: throw Exception("Firebase account error")
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("GoogleActivity", "Google sign in failed", e)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth

        setContent {
            BodyWellBeingTheme {
                Scaffold(content = {
                    var emailText by remember { mutableStateOf("") }
                    var passwordText by remember { mutableStateOf("") }
                    val loginButtonState = remember { mutableStateOf(false) }
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Center)
                                    .fillMaxWidth()
                            ) {
                                FormInputField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = Layout1, end = Layout1, bottom = Layout1),
                                    type = InputFieldType.Text,
                                    text = emailText,
                                    placeHolder = "Email",
                                    onValueChange = {
                                        emailText = it
                                    }
                                )
                                FormInputField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = Layout1, end = Layout1, bottom = Layout1),
                                    type = InputFieldType.Password,
                                    text = passwordText,
                                    placeHolder = ".........",
                                    onValueChange = {
                                        passwordText = it
                                    }
                                )
                            }
                        }
                        ButtonsBar(
                            mainButton = {
                                ButtonFill(
                                    text = "Login",
                                    isLoading = loginButtonState.value,
                                    onClick = {
                                        loginButtonState.value = true
                                        logUser(
                                            email = emailText,
                                            password = passwordText,
                                            loginButtonState = loginButtonState
                                        )
                                    }
                                )
                                Eyebrow(
                                    text = "Doesn't have an account ? Create one !",
                                    modifier = Modifier
                                        .padding(start = Layout1, end = Layout1, bottom = Basic2)
                                        .clickable {
                                            // TODO create a bottomSheet pour créer un compte
                                        }
                                )
                            },
                            secondaryButton = {
                                ButtonFill(
                                    text = "Login with Google",
                                    onClick = {
                                        signInWithGoogle()
                                    }
                                )
                            }
                        )
                    }
                })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        showGoogleSignInActivityResult.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GoogleActivity", "signInWithCredential:success")
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Oops something went wrong :(", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when (task.isSuccessful) {
                    true -> {
                        Log.d("LoginActivity", "createUserWithEmail:success")
                        // TODO ici lancer l'activity après connexion (le user vient de créer un compte et de se log)
                    }
                    else -> {
                        Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // TODO ici la création a fail
                    }
                }
            }
    }

    private fun logUser(email: String, password: String, loginButtonState: MutableState<Boolean>) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    loginButtonState.value = false
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO ici le log a fail
                }
            }
    }
}