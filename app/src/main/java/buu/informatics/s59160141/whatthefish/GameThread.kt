package buu.informatics.s59160141.whatthefish

import android.view.SurfaceHolder
import android.graphics.Canvas

class GameThread (private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread(){
    private var running: Boolean = false

    //// frames per second, the rate at which you would like to refresh the Canvas
    private val targetFPS = 60

    fun setRunning(isRunning: Boolean) {
        this.running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / targetFPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // locking the canvas allows us to draw on to it
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.gameView.update()
                    this.gameView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                Thread.sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    companion object {
        private var canvas: Canvas? = null
    }
}