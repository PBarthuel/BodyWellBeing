package com.pbarthuel.bodywellbeing.app.ui.component.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalSecondaryTextColor

@Composable
fun TextFieldWithDropdownMenu(
    modifier: Modifier = Modifier,
    items: List<DropdownMenuItem>,
    onItemSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    var dropdownVisibility by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var text by remember {
        mutableStateOf(context.getString(R.string.exercise_type))
    }
    Column(modifier = modifier.wrapContentSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                dropdownVisibility = !dropdownVisibility
            }
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            }
            .fieldBorder(LocalSecondaryTextColor.current)
        ) {
            Text(modifier = Modifier.align(Alignment.CenterStart), text = text)
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = dropdownVisibility,
            onDismissRequest = { dropdownVisibility = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            items.forEachIndexed { index, item ->
                if (index != 0) { Divider() }
                DropdownMenuItem(onClick = {
                    text = item.text
                    dropdownVisibility = false
                    onItemSelected(item.id)
                }) {
                    Body1(text = item.text)
                }
            }
        }
    }
}

data class DropdownMenuItem(
    val id: Int,
    val text: String
)