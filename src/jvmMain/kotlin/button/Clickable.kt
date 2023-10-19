package ru.nsu.ccfit.lsbapp.button

/**
 * Интерфейс для кликов мыши по объектов
 */
interface Clickable {
    /**
     * Нажата ЛЕВАЯ кнопка мыши
     */
    fun leftClick()

    /**
     * Нажата ПРАВАЯ кнопка мыши
     */
    fun rightClick()
}