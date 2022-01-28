package com.pbarthuel.bodywellbeing.app.modules.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.component.CustomCard
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.viewModel.modules.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    profileScreenViewModel: ProfileScreenViewModel,
    context: Context
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(vertical = Layout1)
    ) {
        CustomCard {
            Column {
                Body1(text = "Courgette", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Patate", modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Patate", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
                Body1(text = "Courgette", modifier = Modifier.fillMaxWidth()
                    .clickable {
                        Toast
                            .makeText(context, "Courgette", Toast.LENGTH_LONG)
                            .show()
                    }
                    .padding(Layout1))
                Divider()
            }
        }
    }
}