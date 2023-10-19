package dialog.impl.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ru.nsu.ccfit.lsbapp.component.editBox
import ru.nsu.ccfit.lsbapp.component.selectItem
import ru.nsu.ccfit.lsbapp.dialog.ToolDialog
import ru.nsu.ccfit.lsbapp.tool.InterpolationTool
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbEncodeFilter

class LsbEncodeFilterDialog: ToolDialog(LsbEncodeFilter) {
    private val text = mutableStateOf(LsbEncodeFilter.text)

    override fun updateFilter() {
        LsbEncodeFilter.text = text.value
    }

    @Composable
    override fun settingBox() {
        editBox("Сообщение", text)
    }
}