package com.pbarthuel.bodywellbeing.app.modules.accountCreation.composeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation.AccountCreationViewModel

@Composable
fun CongratsScreen(
    viewModel: AccountCreationViewModel,
    onNextClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ButtonFill(
            modifier = Modifier.fillMaxWidth().padding(HorizontalMargin).align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string._CONTINUE_),
            onClick = onNextClicked
        )
    }
}