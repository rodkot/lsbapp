package ru.nsu.ccfit.lsbapp.component

import androidx.compose.runtime.Composable

interface Renderable {
    @Composable
    fun render();
}