package buu.informatics.s59160141.whatthefish.ar

import android.animation.ObjectAnimator
import android.os.CountDownTimer
import android.view.animation.LinearInterpolator
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.math.Vector3Evaluator
import com.google.ar.sceneform.rendering.ModelRenderable

class F74(demon: Node, ren: ModelRenderable?) {

    var demo = demon
    var animation: ModelAnimator? = null
    private var renderable = ren

    init {
        loopTimerAnimationFish()
        loopTimerMovefish()
    }

    private fun loopTimerAnimationFish() {
        object : CountDownTimer(30000, 100) {
            override fun onTick(millisUntilFinished: Long) { // Tick
                if (animation == null || !animation!!.isRunning) {
                    animateModel("Armature|ArmatureAction")
                }
            }

            override fun onFinish() { // Finish
                loopTimerAnimationFish()
            }
        }.start()
    }

    private fun loopTimerMovefish() {
        object : CountDownTimer(240000, 8000) {
            override fun onTick(millisUntilFinished: Long) { // Tick
                startWalking()
            }

            override fun onFinish() { // Finish
                loopTimerMovefish()
            }
        }.start()
    }

    private fun animateModel(name: String) {
        animation?.let { it ->
            if (it.isRunning) {
                it.end()
            }
        }

        renderable?.let { modelRenderable ->
            val data = modelRenderable.getAnimationData(name)
            animation = ModelAnimator(data, modelRenderable)
            animation?.start()
        }
    }

    fun startWalking() {
        val leftLimit = 0.0f
        val rightLimit = 1.0f
//        val xx = (0..180).random().toFloat()
        val x = (-99..99).random() / 100f
        val z = (10..40).random() / 10f * (-1)
        val y = (17..50).random() / 100f
        val angle = Vector3.angleBetweenVectors(demo?.worldPosition, Vector3(x, y, z))
        if (z > demo!!.worldPosition.z && x > 0) {
            demo?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f + angle)
        } else if (z > demo!!.worldPosition.z && x < 0) {
            demo?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f - angle)
        } else {
            demo?.worldRotation =
                Quaternion.rotationBetweenVectors(demo?.worldPosition, Vector3(x, y, z))
        }
        val objectAnimation = ObjectAnimator()
        objectAnimation.setAutoCancel(true)
        objectAnimation.target = demo
        // All the positions should be world positions
// The first position is the start, and the second is the end.
        objectAnimation.setObjectValues(demo?.worldPosition, Vector3(x, y, z))
        //        Log.i("test123");
// Use setWorldPosition to position andy.
        objectAnimation.setPropertyName("worldPosition")
        // The Vector3Evaluator is used to evaluator 2 vector3 and return the next
// vector3.  The default is to use lerp.
        objectAnimation.setEvaluator(Vector3Evaluator())
        // This makes the animation linear (smooth and uniform).
        objectAnimation.interpolator = LinearInterpolator()
        // Duration in ms of the animation.
        objectAnimation.duration = 2000
        objectAnimation.start()
    }
}