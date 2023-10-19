package ru.nsu.ccfit.lsbapp.draw

import ru.nsu.ccfit.lsbapp.component.PlumImage
import java.awt.Graphics2D

fun PlumImage.clear() {
    val graphics: Graphics2D =  createGraphics()
    graphics.background = java.awt.Color.WHITE
    graphics.clearRect(0, 0, width, height)
    graphics.dispose()
}