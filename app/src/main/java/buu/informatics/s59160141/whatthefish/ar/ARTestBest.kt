package buu.informatics.s59160141.whatthefish.ar

import android.animation.ObjectAnimator
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AlertDialog
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
import kotlinx.android.synthetic.main.activity_artest_best.*

class ARTestBest : AppCompatActivity() {

    lateinit var arFragment: ArFragment
    private lateinit var model: Uri
    private var renderable: ModelRenderable? = null
    private var animator: ModelAnimator? = null
    var check = true
    private var andy: Node? = null
    lateinit var listModel: ArrayList<ModelAnimator?>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artest_best)

        arFragment = sceneform_fragmenttest as ArFragment
        model = Uri.parse("f74.sfb")

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                return@setOnTapArPlaneListener
            }
//            if (check) {
                val anchor = hitResult.createAnchor()
                placeObject(arFragment, anchor, model)
//            }else{
//                Log.i("test123",andy?.worldPosition.toString())
//                startWalking()
//            }
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
            listModel.add(animator)                                                     /////////////////////////////////////
            animator?.start()
        }
    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
//        if(check) {
            ModelRenderable.builder()
                .setSource(fragment.context, model)
                .build()
                .thenAccept {
                    renderable = it
                    addToScene(fragment, anchor, it)
                }
                .exceptionally {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(it.message).setTitle("Error")
                    val dialog = builder.create()
                    dialog.show()
                    return@exceptionally null
                }
//            check = false
//        }
    }

    private fun addToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable) {

        val anchorNode = AnchorNode(anchor)
        val skeletonNode = SkeletonNode()
        skeletonNode.renderable = renderable

        val node = TransformableNode(fragment.transformationSystem)
//        node.worldRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 180f)

        //floating
        node.worldPosition = Vector3(0f,0.17f,0f)

        node.addChild(skeletonNode)
        node.setParent(anchorNode)

        fragment.arSceneView.scene.addChild(anchorNode)

        andy = Node()
        andy = node

    }

    fun countDown(){
        object : CountDownTimer(30000, 100) {
            override fun onTick(millisUntilFinished: Long) { // Tick
                for (i in 0 until listModel.size) {
                    if (listModel[i] == null || !listModel[i]!!.isRunning) {
                        animateModel("Armature|ArmatureAction")
                    }
                }
            }
            override fun onFinish() { // Finish
                countDown()
            }
        }.start()
    }

    private fun startWalking() {
        val leftLimit = 0.0f
        val rightLimit = 1.0f
//        val xx = (0..180).random().toFloat()
        val x = (-99..99).random()/100f
        val z = (10..40).random()/10f * (-1)
        val y = (17..50).random()/100f
        val angle = Vector3.angleBetweenVectors(andy?.worldPosition, Vector3(x, y, z))
        if (z > andy!!.worldPosition.z && x > 0){
            andy?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f + angle)
        }else if(z > andy!!.worldPosition.z && x < 0) {
            andy?.worldRotation = Quaternion(Vector3(0f, 1f, 0f), 180f - angle)
        }else{
            andy?.worldRotation = Quaternion.rotationBetweenVectors(andy?.worldPosition, Vector3(x, y, z))
        }
        val objectAnimation = ObjectAnimator()
        objectAnimation.setAutoCancel(true)
        objectAnimation.target = andy
        // All the positions should be world positions
// The first position is the start, and the second is the end.
        objectAnimation.setObjectValues(andy?.worldPosition, Vector3(x, y, z))
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
        andy?.worldPosition = Vector3(x, y, z)
    }
}