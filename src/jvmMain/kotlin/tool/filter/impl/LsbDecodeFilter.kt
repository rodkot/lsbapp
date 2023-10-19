package ru.nsu.ccfit.lsbapp.tool.filter.impl

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.lsbapp.component.Menu
import ru.nsu.ccfit.lsbapp.component.PlumImage
import ru.nsu.ccfit.lsbapp.dialog.impl.AboutDialog
import ru.nsu.ccfit.lsbapp.tool.filter.Filter
import java.awt.Color
import java.io.IOException


object LsbDecodeFilter : Filter("Lsb-извлечение") {

    private fun getLSBBits(pixelRgb: Int): String {
        val c = Color(pixelRgb)
        var result = ""
        val redInB = Integer.toBinaryString(c.red)
        val greenInB = Integer.toBinaryString(c.green)
        val blueInB = Integer.toBinaryString(c.blue)
        result += redInB[redInB.length - 1].toString()
        result += greenInB[greenInB.length - 1].toString()
        result += blueInB[blueInB.length - 1].toString()
        return result
    }


    fun decode(image: PlumImage): String {
        try {
            var secretLength = 20
            var count = 0 //Used to count the number of LSB bit be gotten
            var secret = "" //The secret in binary string format
            for (x in 0 until image.width) {
                for (y in 0 until image.height) {
                    secret += getLSBBits(image.getRGB(x, y))
                    count += 3
                    if (count == 9) {
                        secretLength = secret.substring(0, 8).toInt(2) + 1
                    }
                    if (count > secretLength * 8) break //Break when read all hidden message
                }
                if (count > secretLength) break
            }
            secret = secret.substring(0, secretLength * 8) //Get exactly the length of 'Secret'
            var result = "" //The secret in plaintext
            var i = 8
            while (i < secret.length) {
                result += binToChar(secret.substring(i, i + 8))
                i += 8
            }
            return result
        } catch (ioe: IOException) {
            System.err.println(ioe.message)
        }
        return ""
    }

    //Construct character from its Binary String
    private fun binToChar(binString: String): Char {
        var r = 0
        var n = binString.length
        var i = 0
        var pow = 1
        for (c in binString.toCharArray()) {
            if (c == '1') {
                i = 0
                pow = 1
                while (i < n - 1) {
                    pow *= 2
                    i++
                }
                r += pow
            }
            n--
        }
        return Char(r.toUShort())
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun permit(image: PlumImage): PlumImage {
        val decode = decode(image)
        val open = mutableStateOf(true)
        val dialog = remember { open }
        if (dialog.value) {
            AlertDialog(onDismissRequest = {},
                title = {Text("Сообщение")},
                buttons = {
                    Button(modifier = Modifier.fillMaxWidth().padding(16.dp),
                        onClick = {
                            open.value = false;
                        }
                    ) {
                        Text("Хорошо")
                    }
                }, text = {
                    Text(decode)
                })
        }


        return image;
    }
}