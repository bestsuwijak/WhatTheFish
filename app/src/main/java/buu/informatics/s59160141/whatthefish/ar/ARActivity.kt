package buu.informatics.s59160141.whatthefish.ar

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.whatthefish.R
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.SkeletonNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar.*

class ARActivity : AppCompatActivity() {

    lateinit var arFragment: ArFragment
    private lateinit var model: Uri
    private var renderable: ModelRenderable? = null
    private var animator: ModelAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arFragment = sceneform_fragment as ArFragment
        model = Uri.parse("reF74.sfb")

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                return@setOnTapArPlaneListener
            }
            val anchor = hitResult.createAnchor()
            placeObject(arFragment, anchor, model)
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
    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
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
    }

    private fun addToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable) {
        val anchorNode = AnchorNode(anchor)

        val skeletonNode = SkeletonNode()
        skeletonNode.renderable = renderable

        val node = TransformableNode(fragment.transformationSystem)
        node.addChild(skeletonNode)
        node.setParent(anchorNode)

        fragment.arSceneView.scene.addChild(anchorNode)
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
}
