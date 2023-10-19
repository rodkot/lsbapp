package ru.nsu.ccfit.lsbapp.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuScope
import ru.nsu.ccfit.lsbapp.tool.filter.Filter
import ru.nsu.ccfit.lsbapp.tool.InterpolationTool
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbDecodeFilter
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbEncodeFilter

object Menu {
    var save = mutableStateOf(false)
    var open = mutableStateOf(false)
    var filter = mutableStateOf<Filter>(LsbEncodeFilter)
    var instruction = mutableStateOf(false)
    var interpolation = mutableStateOf(false)
    var about = mutableStateOf(false)

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun render(frameWindowScope: FrameWindowScope) {
        frameWindowScope.MenuBar {
            Menu("Файл", mnemonic = 'F') {
                Item(
                    "Сохранить",
                    onClick = { save.value = true },
                    shortcut = KeyShortcut(Key.S, ctrl = true)
                )
                Item(
                    "Открыть",
                    onClick = { open.value = true },
                    shortcut = KeyShortcut(Key.P, ctrl = true)
                )
            }
            Menu("Фильтры", mnemonic = 'I') {
                // TODO По добавлению фильтра
                // Здесь необходимо добавить схожую конструкцию
                  createFilterMenuItem(LsbEncodeFilter)
                  createFilterMenuItem(LsbDecodeFilter)
            }
            Menu("Инструменты", mnemonic = 'I') {
                CheckboxItem(
                    InterpolationTool.name,
                    checked = interpolation.value,
                    onCheckedChange = {
                        interpolation.value = !interpolation.value
                    })
            }
            Menu("Справка", mnemonic = 'I') {
                Item("Инструкция", onClick = { instruction.value = true })
                Item("О программе", onClick = { about.value = true })
            }
        }
    }

    /**
     * Функция создания для фильра нового объекта меню
     * @param addFilter добавлямый фильтр
     */
    @Composable
    fun MenuScope.createFilterMenuItem(addFilter: Filter) =
        CheckboxItem(
            addFilter.name,
            checked = filter.value == addFilter,
            onCheckedChange = {
                filter.value = addFilter
            }
        )


}
