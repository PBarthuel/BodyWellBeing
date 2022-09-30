package com.pbarthuel.bodywellbeing.app.ui.component

import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header4
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme

val SegmentedControlHeight = 36.dp
private const val SHORT_ELEMENT_SIZE = 3
private const val NUMBER_OF_ELEMENTS_FOR_SHORT = 4

@Composable
fun SegmentedControl(
    entry: List<String>,
    selectedItemIndex: Int,
    onItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BodyWellBeingTheme.colors.surfaceUnchecked,
    selectedItemColor: Color = BodyWellBeingTheme.colors.surfaceChecked,
    shape: Shape = RoundedCornerShape(SegmentedControlHeight / 2),
    selectedItemShape: Shape = MaterialTheme.shapes.medium
) {
    val safeSelectedItemIndex = selectedItemIndex.coerceIn(0, entry.lastIndex)
    var size by remember { mutableStateOf(IntSize.Zero) }
    val animatedPosition: Dp by animateDpAsState(
        targetValue = (size.width / entry.size * safeSelectedItemIndex).dp,
        animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
    )
    val maxChar = if (entry.count() >= NUMBER_OF_ELEMENTS_FOR_SHORT) SHORT_ELEMENT_SIZE else Int.MAX_VALUE
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = backgroundColor
    ) {
        Box(modifier = Modifier.height(SegmentedControlHeight)) {
            Row(
                modifier = Modifier
                    .onGloballyPositioned { size = it.size },
                verticalAlignment = Alignment.CenterVertically
            ) {
                entry.forEachIndexed { index, selectableItem ->
                    SegmentedControlItemBackground(
                        modifier = Modifier.weight(1f),
                        text = selectableItem.take(maxChar),
                        onClick = {
                            onItemSelected(index)
                        },
                        isSelected = index == safeSelectedItemIndex
                    )
                }
            }
            SegmentedControlItemSelected(
                text = entry[safeSelectedItemIndex].take(maxChar),
                size = size / entry.size,
                positionX = animatedPosition.value.toInt(),
                color = selectedItemColor,
                shape = selectedItemShape
            )
        }
    }
}

@Composable
fun SegmentedControlItemBackground(modifier: Modifier = Modifier, text: String, onClick: () -> Unit, isSelected: Boolean) {
    Surface(
        modifier = modifier
            .height(SegmentedControlHeight)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        shape = MaterialTheme.shapes.medium,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header4(
                modifier = Modifier
                    .alpha(
                        if (isSelected) {
                            0f
                        } else {
                            1f
                        }
                    ),
                text = text
            )
        }
    }
}

@Composable
fun SegmentedControlItemSelected(
    modifier: Modifier = Modifier,
    text: String,
    size: IntSize,
    positionX: Int,
    color: Color,
    shape: Shape
) {
    Layout(
        modifier = modifier,
        measurePolicy = { measurables, _ ->
            layout(size.width, size.height) {
                require(measurables.size == 1)
                measurables[0]
                    .measure(Constraints.fixedWidth(size.width))
                    .place(positionX, 0)
            }
        },
        content = {
            Surface(
                modifier = modifier
                    .padding(Basic1)
                    .height(SegmentedControlHeight - (2 * Basic1.value).dp),
                shape = shape,
                color = color
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Header4(
                        text = text
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun preview_SegmentedControl() {
    BodyWellBeingTheme {
        SegmentedControl(
            entry = listOf(
                "Choix 1",
                "Example 2",
            ),
            selectedItemIndex = 0,
            onItemSelected = { }
        )
    }
}
