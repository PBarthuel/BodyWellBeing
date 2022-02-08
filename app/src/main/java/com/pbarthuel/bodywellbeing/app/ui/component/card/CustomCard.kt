package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.theme.BottomCardMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin

@Composable
fun CustomCard(
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = BottomCardMargin)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalMargin),
            content = content
        )
    }
}

@Composable
fun CustomCard(
    onCardClicked: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = BottomCardMargin)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalMargin)
                .clickable { onCardClicked() },
            content = content
        )
    }
}