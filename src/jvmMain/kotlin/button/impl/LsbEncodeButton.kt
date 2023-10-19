package ru.nsu.ccfit.lsbapp.button.impl

import compose.icons.FeatherIcons
import compose.icons.feathericons.Lock
import compose.icons.feathericons.Maximize
import dialog.impl.tools.LsbEncodeFilterDialog
import ru.nsu.ccfit.lsbapp.button.FilterButton
import ru.nsu.ccfit.lsbapp.button.FilterSettingButton
import ru.nsu.ccfit.lsbapp.icon.impl.ImageIcon
import ru.nsu.ccfit.lsbapp.icon.impl.VectorIcon
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbEncodeFilter

class LsbEncodeButton(checked: Boolean, onClick: () -> Unit) :
    FilterSettingButton(LsbEncodeFilter,LsbEncodeFilterDialog(), checked, VectorIcon(
    FeatherIcons.Lock), onClick)