package buu.informatics.s59160141.whatthefish.gameview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

class Background (private val image: Bitmap){
    private var x = 0f
    private var y = 0f
    private val w: Int
    private val h: Int
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        w = image.width
        h = image.height

//        Log.i("sizescreen", w.toString())
//        Log.i("sizescreen", h.toString())
//        Log.i("sizescreen", screenWidth.toString())
//        Log.i("sizescreen", screenHeight.toString())

//        x = screenWidth/2
//        y = screenHeight - 200

        x = 0f
        y = 0f
    }

    /**
     * Draws the object on to the canvas.
     */
    fun draw(canvas: Canvas) {
        val dst = RectF(x, y, x + screenWidth, y + screenHeight)
        canvas.drawBitmap(image, null, dst, null)
    }

    /**
     * update properties for the game object
     * when the player touches the screen, position the player bitmap there
     */
//    fun update(touch_x: Int, touch_y: Int) {
//        x = touch_x - w / 2
//        y = touch_y - h / 2
//    }
}