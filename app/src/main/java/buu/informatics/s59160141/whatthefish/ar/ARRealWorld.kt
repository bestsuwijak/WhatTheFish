package buu.informatics.s59160141.whatthefish.ar

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import buu.informatics.s59160141.whatthefish.R
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.SkeletonNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.math.Vector3Evaluator
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar2.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class ARRealWorld : AppCompatActivity() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    lateinit var arFragment: ArFragment
    private lateinit var model: Uri
    private var renderable: ModelRenderable? = null
    private var renderable2: ModelRenderable? = null
    private var animator: ModelAnimator? = null
    private var animator2: ModelAnimator? = null
    var check = true
//    private var andy: F74? = null
    private var andy2: F74? = null
    lateinit var andy: ArrayList<F74>
    private var countAndy = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)

        arFragment = sceneform_fragment_ar2 as ArFragment
        model = Uri.parse("f74.sfb")

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                return@setOnTapArPlaneListener
            }
            if (check) {
                val anchor = hitResult.createAnchor()
                placeObject(arFragment, anchor, model)
            }else{
//                Log.i("test123",andy?.worldPosition.toString())
                andy[0].startWalking()
                andy[1].startWalking()
            }
        }


        countDown()
//        animate_kick_button.setOnClickListener { animateModel("Armature|ArmatureAction") }
    }

    private fun animateModel(name: String) {
        animator?.let { it ->
            if (it.isRunning) {
                it.end()
            }
        }
        renderable?.let { modelRenderable ->
            val data = modelRenderable.getAnimationData(name)
            animator = ModelAnimator(data, modelRenderable)
            animator?.start()
        }

        animator2?.let { it ->
            if (it.isRunning) {
                it.end()
            }
        }
        renderable2?.let { modelRenderable ->
            val data = modelRenderable.getAnimationData(name)
            animator2 = ModelAnimator(data, modelRenderable)
            animator2?.start()
        }
    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
        if(check) {
//            for (i in 0 until 2){
                ModelRenderable.builder()
                    .setSource(fragment.context, model)
                    .build()
                    .thenAccept {
                        renderable = it
                        addToScene(fragment, anchor, it, 0)
                    }
                    .exceptionally {
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage(it.message).setTitle("Error")
                        val dialog = builder.create()
                        dialog.show()
                        return@exceptionally null
                    }

            ModelRenderable.builder()
                .setSource(fragment.context, model)
                .build()
                .thenAccept {
                    renderable2 = it
                    addToScene(fragment, anchor, it, 1)
                }
                .exceptionally {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(it.message).setTitle("Error")
                    val dialog = builder.create()
                    dialog.show()
                    return@exceptionally null
                }
//            }
                check = false
        }
    }

    private fun addToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable, i: Int) {

        val anchorNode = AnchorNode(anchor)
        val skeletonNode = SkeletonNode()
        skeletonNode.renderable = renderable

        val node = TransformableNode(fragment.transformationSystem)
//        node.worldRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 180f)

        //floating

        viewModelScope.launch {
            if (i == 0){
                node.worldPosition = Vector3(0f,0.17f,0f)
                node.addChild(skeletonNode)
                node.setParent(anchorNode)
                fragment.arSceneView.scene.addChild(anchorNode)
                andy = arrayListOf(F74(node))
                Log.i("test123", andy.size.toString())
            }else{
                delay(5000)
                Log.i("test123", andy.size.toString())
                node.worldPosition = Vector3(0f,0f,0f)
                node.addChild(skeletonNode)
                node.setParent(anchorNode)
                fragment.arSceneView.scene.addChild(anchorNode)
                andy.add(F74(node))
            }
        }

    }

    fun countDown(){
        object : CountDownTimer(30000, 100) {
            override fun onTick(millisUntilFinished: Long) { // Tick
                if (animator == null || !animator!!.isRunning) {
                    animateModel("Armature|ArmatureAction")
                }
            }
            override fun onFinish() { // Finish
                countDown()
            }
        }.start()
    }

//    private fun startWalking() {
//        val leftLimit = 0.0f
//        val rightLimit = 1.0f
////        val xx = (0..180).random().toFloat()
//        val x = (-99..99).random()/100f
//        val z = (10..40).random()/10f * (-1)
//        val y = (17..50).random()/100f
//        val angle = Vector3.angleBetweenVectors(andy?.worldPosition, Vector3(x, y, z))
//        if (z > andy!!.worldPosition.z && x > 0){
//            andy?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f + angle)
//        }else if(z > andy!!.worldPosition.z && x < 0) {
//            andy?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f - angle)
//        }else{
//            andy?.worldRotation = Quaternion.rotationBetweenVectors(andy?.worldPosition, Vector3(x, y, z))
//        }
//        val objectAnimation = ObjectAnimator()
//        objectAnimation.setAutoCancel(true)
//        objectAnimation.target = andy
//        // All the positions should be world positions
//// The first position is the start, and the second is the end.
//        objectAnimation.setObjectValues(andy?.worldPosition, Vector3(x, y, z))
//        //        Log.i("test123");
//// Use setWorldPosition to position andy.
//        objectAnimation.setPropertyName("worldPosition")
//        // The Vector3Evaluator is used to evaluator 2 vector3 and return the next
//// vector3.  The default is to use lerp.
//        objectAnimation.setEvaluator(Vector3Evaluator())
//        // This makes the animation linear (smooth and uniform).
//        objectAnimation.interpolator = LinearInterpolator()
//        // Duration in ms of the animation.
//        objectAnimation.duration = 2000
//        objectAnimation.start()
//    }
}