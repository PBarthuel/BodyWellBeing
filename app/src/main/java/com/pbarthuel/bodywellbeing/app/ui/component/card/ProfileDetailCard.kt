package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body2
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.BottomCardMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin

@Composable
fun ProfileDetailCard(
    @DrawableRes drawableId: Int,
    displayName: String,
    info: String
) {
    CustomCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalMargin, vertical = VerticalMargin)
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = drawableId),
                contentDescription = "Empty section card content"
            )
            Spacer(modifier = Modifier.size(Basic3))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Header1(modifier = Modifier.padding(bottom = Basic1), text = displayName)
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = BottomCardMargin)
                ) {
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth(),
                        content = {
                            Column(modifier = Modifier.background(BodyWellBeingTheme.colors.actionSecondary)) {
                                Body2(
                                    text = info,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(Basic3)
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}