package com.pbarthuel.bodywellbeing.app.ui.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1

@Composable
fun ButtonsBar(
    shouldReactToKeyboardPadding: Boolean = true,
    mainButton: @Composable () -> Unit = { },
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = BodyWellBeingTheme.colors.divider
    )
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1, top = Basic3)
            .then(if (shouldReactToKeyboardPadding) Modifier.navigationBarsWithImePadding() else Modifier.navigationBarsPadding()),
        verticalArrangement = Arrangement.spacedBy(Basic2)
    ) {
        mainButton()
        secondaryButton?.invoke()
    }
}
