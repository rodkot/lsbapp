package ru.nsu.ccfit.lsbapp.tool.filter.impl

import androidx.compose.runtime.Composable
import ru.nsu.ccfit.lsbapp.component.PlumImage
import ru.nsu.ccfit.lsbapp.tool.filter.Filter
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


object LsbEncodeFilter : Filter("Lsb-внедрение") {
    var text = "hello"

    fun padding(b: Int): String? {
        var bin = Integer.toBinaryString(b)
        while (bin.length < 8) {
            bin = "0$bin"
        }
        return bin
    }
    fun messageToBinary(msg: String): String {
        var result: String? = ""
        for (c in msg.toCharArray()) {
            result += padding(c.code)
        }
        //Padding secret to its length is common multiple by 3
        while (result!!.length % 3 != 0) {
            result += "0"
        }
        return result
    }

    fun encode(image: PlumImage, secret: String) {
        try {
            val msg_length = secret.length //Number characters in Secret string
            var count = 0
            //Get file name without file extension
            var hiddenString: String = padding(msg_length) + messageToBinary(secret)
            while (hiddenString.length % 3 != 0) {
                hiddenString += "0"
            }
            val binHidden = hiddenString.toCharArray() //Convert secret string to binary char array
            val break_point = binHidden.size //Length secret message in binary
            for (x in 0 until image.width) {
                for (y in 0 until image.height) {
                    var rgb = image.getRGB(x, y) //Get current RGB value
                    rgb = if (binHidden[count++] == '0') rgb and -0x10001 else rgb or 0x00010000 //Replace LSB Red value
                    rgb = if (binHidden[count++] == '0') rgb and -0x101 else rgb or 0x00000100 //Replace LSB Green value
                    rgb = if (binHidden[count++] == '0') rgb and -0x2 else rgb or 0x00000001 //Replace LSB Blue value
                    image.setRGB(x, y, rgb) //Set new RGB value
                    if (count == break_point) break
                }
                if (count == break_point) break //Check for breaking the loop when encoding all secret charaters
            }
        } catch (ioe: IOException) {
            System.err.println(ioe.message)
        }
    }


    @Composable
    override fun permit(image: PlumImage): PlumImage {
        val newImage = image.copy()
        encode(newImage, text)
        return newImage;
    }
}