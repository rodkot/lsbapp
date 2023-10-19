
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ru.nsu.ccfit.lsbapp.component.Menu
import ru.nsu.ccfit.lsbapp.windows.MainWindow
import java.awt.Dimension

class SizeWindows {
    companion object {
        fun height(): Int = 480
        fun width(): Int = 640
    }
}

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "LsbApp",
        icon = painterResource("plum-logo.png"),
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = SizeWindows.width().dp,
            height = SizeWindows.height().dp
        )
    ) {
        window.minimumSize = Dimension(SizeWindows.width(), SizeWindows.height())
        Menu.render(this)
        MainWindow().render()
    }
}