package ru.nsu.ccfit.lsbapp.dialog

import androidx.compose.runtime.Composable

interface Dialog {
    @Composable
    fun openDialog(confirmClick: () -> Unit, cancelClick: () -> Unit)
}