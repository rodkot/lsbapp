package ru.nsu.ccfit.lsbapp.dialog.impl.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ru.nsu.ccfit.lsbapp.component.selectItem
import ru.nsu.ccfit.lsbapp.dialog.ToolDialog
import ru.nsu.ccfit.lsbapp.tool.InterpolationTool

class InterpolationToolDialog: ToolDialog(InterpolationTool) {
    private val interpolation = mutableStateOf(InterpolationTool.interpolation)

    override fun updateFilter() {
        InterpolationTool.interpolation = interpolation.value
    }

    @Composable
    override fun settingBox() {
        selectItem("Режим", interpolation, InterpolationTool.Interpolation.values())

    }
}