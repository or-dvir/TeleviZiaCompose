package com.hotmail.or_dvir.televiziacompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.doInScope(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    action: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(dispatcher) { action() }