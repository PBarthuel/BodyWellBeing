package com.pbarthuel.bodywellbeing.viewModel.utils

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Singleton
class CoroutineToolsProvider @Inject constructor() {
    val io: CoroutineDispatcher = Dispatchers.IO
    val main: CoroutineDispatcher = Dispatchers.Main
}