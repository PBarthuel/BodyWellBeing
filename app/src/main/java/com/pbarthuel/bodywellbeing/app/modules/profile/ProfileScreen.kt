package com.pbarthuel.bodywellbeing.app.modules.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.ui.component.ProfileDetailCard
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel
) {
    val user by viewModel.user.collectAsState(initial = User())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = VerticalMargin)
    ) {
        ProfileDetailCard(
            drawableId = R.drawable.ic_launcher_background,
            displayName = "Paul",
            info = user.email
        )
    }
}