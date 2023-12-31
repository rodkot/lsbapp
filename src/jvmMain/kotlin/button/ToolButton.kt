package ru.nsu.ccfit.lsbapp.button

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.lsbapp.component.Renderable
import ru.nsu.ccfit.lsbapp.icon.Icon
import ru.nsu.ccfit.lsbapp.tool.Tool

/**
 * Конструктор кнопки для инструментов
 * @param tool  инстумент для которого настраивается кнопка
 * @param checked boolean указывающий выбрана ли кнопка
 * @param icon иконка для кнопки
 * @see Tool
 */
abstract class ToolButton(
    private val tool: Tool,
    private val checked: Boolean,
    private val icon: Icon
) : Renderable, Clickable {

    @OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
    @Composable
    override fun render() {
        TooltipArea(
            tooltip = {
                Surface(
                    modifier = Modifier.shadow(4.dp),
                    color = Color(0, 0, 0, 255),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = tool.name,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            },
            modifier = Modifier.padding(10.dp),
            delayMillis = 600,
            tooltipPlacement = TooltipPlacement.CursorPoint(
                alignment = Alignment.BottomEnd
            )
        ) {
            Box(
                modifier = Modifier
                    .run { if (checked) this.background(Color(153, 124, 246), CircleShape) else this }
            ) {
                IconToggleButton(modifier = Modifier.pointerInput(Unit) {
                    forEachGesture {
                        awaitPointerEventScope {
                            val event = awaitPointerEvent()
                            if (event.button == PointerButton.Secondary)
                                rightClick()
                        }
                    }
                }, checked = checked, onCheckedChange = { leftClick() }) {
                    icon.render()
                }
            }
        }
    }
}
