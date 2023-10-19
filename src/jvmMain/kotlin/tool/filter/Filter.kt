package ru.nsu.ccfit.lsbapp.tool.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.lsbapp.component.PlumImage
import ru.nsu.ccfit.lsbapp.tool.Tool

abstract class Filter(name: String) : Tool(name) {
    /**
     * Функция применяет фильр к изображению
     * @param image исходное изображение
     * @return возвращает отфильрованное изображение
     */
    @Composable
    abstract fun permit(image: PlumImage): PlumImage

    @Composable
    final override fun draw(
        image: PlumImage,
        pressOffset: Offset,
        releaseOffset: Offset,
        size: IntSize
    ): PlumImage {
        return permit(image)
    }
}