package ru.nsu.ccfit.lsbapp.component

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.lsbapp.button.impl.InterpolationButton
import ru.nsu.ccfit.lsbapp.button.impl.LsbDecodeButton
import ru.nsu.ccfit.lsbapp.button.impl.LsbEncodeButton
import ru.nsu.ccfit.lsbapp.component.Renderable
import ru.nsu.ccfit.lsbapp.component.defaultScrollbarStyle
import ru.nsu.ccfit.lsbapp.tool.filter.Filter
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbDecodeFilter
import ru.nsu.ccfit.lsbapp.tool.filter.impl.LsbEncodeFilter


class ToolBar(
    private val currentFilter: MutableState<Filter>,
    private val interpolation: MutableState<Boolean>
) : Renderable {

    @Composable
    override fun render() {
        TopAppBar(title = {

        }, modifier = Modifier.fillMaxWidth(),
            actions = {
                Row(Modifier.weight(5f)) {
                    Box(Modifier.fillMaxWidth()){
                        val stateHorizontal = rememberScrollState(0)
                    Column(Modifier.fillMaxWidth().horizontalScroll(stateHorizontal).padding(bottom = 3.dp)) {
                        Row(Modifier.align(Alignment.CenterHorizontally)) {
                            // TODO По добавлению фильтра
                            // Добавить схожую конструкцию
                            LsbEncodeButton(currentFilter.value is LsbEncodeFilter) {
                                currentFilter.value = LsbEncodeFilter
                            }.render()

                            LsbDecodeButton(currentFilter.value is LsbDecodeFilter) {
                                currentFilter.value = LsbDecodeFilter
                            }.render()
                        }
                    }
                        HorizontalScrollbar(
                            modifier = Modifier.align(Alignment.BottomStart)
                                .fillMaxWidth(),
                            style = defaultScrollbarStyle(),
                            adapter = rememberScrollbarAdapter(stateHorizontal)
                        )
                    }

                }

                Row(Modifier.weight(1f)) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(Modifier.align(Alignment.End)) {
                            InterpolationButton(interpolation.value) {
                                interpolation.value = !interpolation.value
                            }.render()
                        }
                    }

                }
            })
    }
}