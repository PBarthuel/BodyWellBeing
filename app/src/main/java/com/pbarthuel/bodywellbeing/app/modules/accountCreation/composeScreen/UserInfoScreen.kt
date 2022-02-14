package com.pbarthuel.bodywellbeing.app.modules.accountCreation.composeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation.AccountCreationViewModel

@Composable
fun UserInfoScreen(
    viewModel: AccountCreationViewModel,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        tint = MaterialTheme.colors.onSurface,
                        contentDescription = "Back button"
                    )
                }
            },
            backgroundColor = Color.Transparent.copy(alpha = 0f),
            elevation = 0.dp,
            title = { Header2(text = "Courgette") },
        )
        Divider()
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = VerticalMargin)
        ) {
            FormInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                label = stringResource(id = R.string.first_name),
                type = InputFieldType.Text,
                text = viewModel.firstName.value,
                placeHolder = stringResource(id = R.string.first_name_placeholder),
                onValueChange = {
                    viewModel.firstName.value = it
                }
            )
            FormInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                label = stringResource(id = R.string.last_name),
                type = InputFieldType.Text,
                text = viewModel.lastName.value,
                placeHolder = stringResource(id = R.string.last_name_placeholder),
                onValueChange = {
                    viewModel.lastName.value = it
                }
            )
            FormInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                label = stringResource(id = R.string.age),
                type = InputFieldType.Number,
                text = viewModel.age.value,
                placeHolder = stringResource(id = R.string.age_placeholder),
                onValueChange = {
                    viewModel.age.value = it
                }
            )
            FormInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                label = stringResource(id = R.string.height),
                type = InputFieldType.Number,
                text = viewModel.height.value,
                placeHolder = stringResource(id = R.string.height_placeholder),
                onValueChange = {
                    viewModel.height.value = it
                }
            )
            FormInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                label = stringResource(id = R.string.weight),
                type = InputFieldType.Number,
                text = viewModel.weight.value,
                placeHolder = stringResource(id = R.string.weight_placeholder),
                onValueChange = {
                    viewModel.weight.value = it
                }
            )
        }
        ButtonsBar(
            mainButton = {
                ButtonFill(
                    text = stringResource(id = R.string._CONTINUE_),
                    enabled = viewModel.firstName.value.isNotEmpty()
                            && viewModel.lastName.value.isNotEmpty()
                            && viewModel.age.value.isNotEmpty()
                            && viewModel.height.value.isNotEmpty()
                            && viewModel.weight.value.isNotEmpty(),
                    onClick = onNextClicked
                )
            }
        )
    }
}