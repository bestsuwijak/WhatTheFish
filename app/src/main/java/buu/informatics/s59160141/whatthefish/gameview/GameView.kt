package buu.informatics.s59160141.whatthefish.gameview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import buu.informatics.s59160141.whatthefish.R

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {
    private var thread: GameThread
    private var bubble1: Bubble? = null
    private var bubble2: Bubble? = null
    private var bubble3: Bubble? = null
    private var bubble4: Bubble? = null
    private var bubble5: Bubble? = null
    private var yellowFish1: YellowFish? = null
    private var yellowFish2: YellowFish? = null
    private var yellowFish3: YellowFish? = null
    private var yellowFish4: YellowFishR2L? = null
    private var demo1: YellowFish? = null
    private var demo2: YellowFish? = null
    private var demo3: YellowFishR2L? = null
    private var demo4: YellowFishR2L? = null
    private var demo5: YellowFishR2L? = null
    private var bigFish1: YellowFish? = null
    private var bigFish2: YellowFishR2L? = null
    private var blueFish1: YellowFish? = null
    private var blueFish2: YellowFish? = null
    private var blueFish3: YellowFishR2L? = null
    private var blueFish4: YellowFishR2L? = null
    private var background: Background? = null

    private var touched_x: Int = 0
    private var touched_y: Int = 0

    init {

        // add callback
        holder.addCallback(this)

        // instantiate the game thread
        thread =
            GameThread(
                holder,
                this
            )
    }


    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        // add callback
        holder.addCallback(this)
        // instantiate the game thread
        thread =
            GameThread(
                holder,
                this
            )

        // game objects
        background =
            Background(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.background
                )
            )

        bubble1 = Bubble(BitmapFactory.decodeResource(resources,
            R.drawable.bubble
        ))
        bubble2 = Bubble(BitmapFactory.decodeResource(resources,
            R.drawable.bubble
        ))
        bubble3 = Bubble(BitmapFactory.decodeResource(resources,
            R.drawable.bubble
        ))
        bubble4 = Bubble(BitmapFactory.decodeResource(resources,
            R.drawable.bubble
        ))
        bubble5 = Bubble(BitmapFactory.decodeResource(resources,
            R.drawable.bubble
        ))

        yellowFish1 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish1
                ),
                1
            )
        yellowFish2 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish1
                ),
                1
            )
        yellowFish3 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish1
                ),
                1
            )
        yellowFish4 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f1_revert
                ),
                1
            )
        demo1 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish15
                ),
                15
            )
        demo2 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish15
                ),
                15
            )
        demo3 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f15_revert
                ),
                15
            )
        demo4 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f15_revert
                ),
                15
            )
        demo5 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f15_revert
                ),
                15
            )
        bigFish1 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish20
                ),
                20
            )
        bigFish2 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f20_revert
                ),
                20
            )
        blueFish1 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish5
                ),
                5
            )
        blueFish2 =
            YellowFish(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.fish5
                ),
                5
            )
        blueFish3 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f5_revert
                ),
                5
            )
        blueFish4 =
            YellowFishR2L(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.f5_revert
                ),
                5
            )

        // start the game thread
        thread.setRunning(true)
        if (!thread.isAlive()) {
            thread.start()
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    /**
     * Function to update the positions of player and game objects
     */
    fun update() {
        bubble1!!.update()
        bubble2!!.update()
        bubble3!!.update()
        bubble4!!.update()
        bubble5!!.update()
        yellowFish1!!.update()
        yellowFish2!!.update()
        yellowFish3!!.update()
        yellowFish4!!.update()
        demo1!!.update()
        demo2!!.update()
        demo3!!.update()
        demo4!!.update()
        demo5!!.update()
        bigFish1!!.update()
        bigFish2!!.update()
        blueFish1!!.update()
        blueFish2!!.update()
        blueFish3!!.update()
        blueFish4!!.update()

    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        background!!.draw(canvas)
        bubble1!!.draw(canvas)
        bubble2!!.draw(canvas)
        bubble3!!.draw(canvas)
        bubble4!!.draw(canvas)
        bubble5!!.draw(canvas)
        yellowFish1!!.draw(canvas)
        yellowFish2!!.draw(canvas)
        yellowFish3!!.draw(canvas)
        yellowFish4!!.draw(canvas)
        demo1!!.draw(canvas)
        demo2!!.draw(canvas)
        demo3!!.draw(canvas)
        demo4!!.draw(canvas)
        demo5!!.draw(canvas)
        bigFish1!!.draw(canvas)
        bigFish2!!.draw(canvas)
        blueFish1!!.draw(canvas)
        blueFish2!!.draw(canvas)
        blueFish3!!.draw(canvas)
        blueFish4!!.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // when ever there is a touch on the screen,
        // we can get the position of touch
        // which we may use it for tracking some of the game objects
        touched_x = event.x.toInt()
        touched_y = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                yellowFish1?.checkOnClickListener(touched_x, touched_y)
                yellowFish2?.checkOnClickListener(touched_x, touched_y)
                yellowFish3?.checkOnClickListener(touched_x, touched_y)
                yellowFish4?.checkOnClickListener(touched_x, touched_y)
                demo1?.checkOnClickListener(touched_x, touched_y)
                demo2?.checkOnClickListener(touched_x, touched_y)
                demo3?.checkOnClickListener(touched_x, touched_y)
                demo4?.checkOnClickListener(touched_x, touched_y)
                demo5?.checkOnClickListener(touched_x, touched_y)
                bigFish1?.checkOnClickListener(touched_x, touched_y)
                bigFish2?.checkOnClickListener(touched_x, touched_y)
                blueFish1?.checkOnClickListener(touched_x, touched_y)
                blueFish2?.checkOnClickListener(touched_x, touched_y)
                blueFish3?.checkOnClickListener(touched_x, touched_y)
                blueFish4?.checkOnClickListener(touched_x, touched_y)
            }
        }
        return true
    }
}