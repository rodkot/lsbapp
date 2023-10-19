package ru.nsu.ccfit.lsbapp.button.impl

import compose.icons.FeatherIcons
import compose.icons.feathericons.Lock
import compose.icons.feathericons.Unlock
import ru.nsu.ccfit.lsbapp.button.FilterButton
import ru.nsu.ccfit.lsbapp.icon.impl.VectorIcon
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbDecodeFilter
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbEncodeFilter

class LsbDecodeButton(checked: Boolean, onClick: () -> Unit) :
    FilterButton(
        LsbDecodeFilter, checked, VectorIcon(
            FeatherIcons.Unlock
        ), onClick
    )