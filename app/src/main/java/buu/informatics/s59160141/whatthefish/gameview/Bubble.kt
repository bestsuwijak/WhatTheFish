package buu.informatics.s59160141.whatthefish.gameview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class Bubble(var image: Bitmap) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var speed = 0f
    var x = 0f
    var y = 0f
    var yEnd = 0f
    var yCooldown = 0f
    var w: Int = 0
    var h: Int = 0
    var f: Int = 0
    var pw: Int = 0
    var ph: Int = 0
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels


    init {
        w = image.width
        h = image.height

        speed = 15f

        x = (80..(screenWidth - 80)).random().toFloat()
        y = randomBorn().toFloat()
        yEnd = (screenHeight * 4) + y

        pw = screenWidth / 16
        ph = screenHeight / 10


    }

    private fun randomBorn(): Int {
        return ((screenHeight/3)*2..screenHeight).random()
    }

    fun draw(canvas: Canvas) {
//        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
        val dst = RectF(x, y, x + pw, y + ph)
        canvas.drawBitmap(image, null, dst, null)
    }

    fun update() {
        if (yCooldown <= yEnd*(-1)) {
            x = (80..(screenWidth - 80)).random().toFloat()
            y = randomBorn().toFloat()
            f = 0
            yEnd = (screenHeight * 4) + y
            yCooldown = 0f
        }

        if (f == 1) {
            yCooldown -= speed
        }else {
            if (y < (screenHeight + 50)*(-1)) {
                yCooldown = y
                f = 1
            }else {
                y -= speed
            }
        }


    }

}