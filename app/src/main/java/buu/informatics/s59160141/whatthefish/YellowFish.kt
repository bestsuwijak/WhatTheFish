package buu.informatics.s59160141.whatthefish

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import kotlin.math.abs
import android.graphics.RectF
import android.util.Log


class YellowFish (var image: Bitmap, val fishType: Int) {
    var speed = 0f
    var x = 0f
    var y = 0f
    var w: Int = 0
    var h: Int = 0
    var t: Int = 0
    var pw: Int = 0
    var ph: Int = 0


//    val left = 100f
//    val top = 300f


    private var xVelocity = 20
    private var yVelocity = 20
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height

        speed = ranSpeed()

        x = (200..1000).random().toFloat() * (-1)
        y = randomBorn().toFloat()

        when (fishType) {
            1 -> {
                pw = screenWidth / 7
                ph = screenHeight / 6
            }
            5 -> {
                pw = screenWidth / 5
                ph = screenHeight / 6
            }
            15 -> {
                pw = screenWidth / 7
                ph = screenHeight / 7
            }
            20 -> {
                pw = screenWidth / 4
                ph = screenHeight / 3
            }
        }

    }

    private fun randomBorn(): Int {
        return (100..660).random()
    }

    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
//        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
        val dst = RectF(x, y, x + pw, y + ph)
        canvas.drawBitmap(image, null, dst, null)
    }

    /**
     * update properties for the game object
     */
    fun update() {
        // val randomNum = ThreadLocalRandom.current().nextInt(1, 5)

//        if (x > screenWidth - image.width || x < image.width) {
//            xVelocity = xVelocity * -1
//        }
//        if (y > screenHeight - image.height || y < image.height) {
//            yVelocity = yVelocity * -1
//        }
        if(x - screenWidth > 400){
            x = (200..5000).random().toFloat() * (-1)
            t = 0
            y = randomBorn().toFloat()
            speed = ranSpeed()
        }

        if (t == 1){
            x += (7..10).random()
        }else{
            x += speed
        }
//        x += 1
//        y += 0

    }

    fun checkOnClickListener(touchedX: Int, touchedY: Int){
        if (abs(touchedX - this.x) < 201 && abs(touchedY - this.y) < 101){
            this.t = 1
        }
    }

    fun ranSpeed(): Float{
        val s = listOf(0.39,0.52,0.74,0.3,0.5,0.7,1.13,1.18,1.14,1.2,1.35,1.38,1.4,1.52,1.57,2.05,2.03,2.09,3.07,3.1,5.0)
        return s.random().toFloat()
    }
}