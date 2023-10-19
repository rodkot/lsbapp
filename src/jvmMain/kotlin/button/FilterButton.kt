package ru.nsu.ccfit.lsbapp.button

import ru.nsu.ccfit.lsbapp.tool.filter.Filter
import ru.nsu.ccfit.lsbapp.icon.Icon

/**
 * Конструктор кнопки для фильтра без настроки параметров
 * @param filter фильр для которого настраивается кнопка
 * @param checked boolean указывающий выбрана ли кнопка
 * @param onClick функция вызыывющийся при нажатии кнопки
 * @param icon иконка для кнопки
 * @see Filter
 */
abstract class FilterButton(filter: Filter, checked: Boolean, icon: Icon, private val onClick: () -> Unit) : ToolButton(filter, checked, icon) {
    final override fun rightClick() {}
    final override fun leftClick() {
        onClick.invoke()
    }
}