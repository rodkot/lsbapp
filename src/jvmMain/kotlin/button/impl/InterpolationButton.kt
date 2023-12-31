package ru.nsu.ccfit.lsbapp.button.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import compose.icons.FeatherIcons
import compose.icons.feathericons.Maximize

import ru.nsu.ccfit.lsbapp.button.ToolButton
import ru.nsu.ccfit.lsbapp.dialog.impl.tools.InterpolationToolDialog
import ru.nsu.ccfit.lsbapp.icon.impl.VectorIcon
import ru.nsu.ccfit.lsbapp.tool.InterpolationTool


class InterpolationButton(check:Boolean,val onClick:()->Unit): ToolButton(InterpolationTool, check, VectorIcon(
    FeatherIcons.Maximize)) {
    private val viewDialog = mutableStateOf(false)
    private val dialog = InterpolationToolDialog()

    final override fun rightClick() {
        viewDialog.value = true
    }

    final override fun leftClick() {
        onClick.invoke()
    }

    private fun closeSetting() {
        viewDialog.value = false
    }

    @Composable
    override fun render() {
        val dialogOpen = remember { viewDialog }
        super.render()
        if (dialogOpen.value) {
            dialog.openDialog({ closeSetting() }, { dialogOpen.value = false })
        }
    }
}