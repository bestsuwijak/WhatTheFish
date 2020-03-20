package buu.informatics.s59160141.whatthefish.ar

import android.content.ContentValues
import android.content.Intent
import android.media.CamcorderProfile
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.ArRealWorldAdapter
import com.google.ar.core.*
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.SkeletonNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar2.*
import kotlin.collections.ArrayList

class ARRealWorld : AppCompatActivity() {

    lateinit var arFragment: ArFragment
    lateinit var recyclerARRealWorld: RecyclerView
    var model = Uri.parse("f74.sfb")
    private var renderable = ArrayList<ModelRenderable>()
    private var animator = ArrayList<ModelAnimator>()
    var isFullscreenPublic = false
    private var andy = ArrayList<F74>()

    //////////////////////Record screen//////////////////////
    private var videoRecorder: VideoRecorder? = null
    ////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)

        //get foundFish list
        val number = intent.getStringArrayListExtra("number")
        Log.i("test123", number.toString())

        arFragment = sceneform_fragment_ar2 as ArFragment
        recyclerARRealWorld = listAR_RealWorld
        recyclerARRealWorld.adapter = ArRealWorldAdapter(this, number, this)
        refresh_button.setOnClickListener {
            refresh()
        }
        record_button.setOnClickListener {
            if (videoRecorder == null) {
                videoRecorder = VideoRecorder()
                val orientation = resources.configuration.orientation
                videoRecorder!!.setVideoQuality(CamcorderProfile.QUALITY_2160P, orientation)
                videoRecorder!!.setSceneView(arFragment.arSceneView)
            }
            val isRecording: Boolean = videoRecorder!!.onToggleRecord()
            if (isRecording) {
                Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show()
                record_button.setImageResource(R.drawable.rcd2)
            } else {
                Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
                record_button.setImageResource(R.drawable.rcd)

                //Toast video path
                val videoPath: String = videoRecorder!!.getVideoPath()!!.absolutePath
                Toast.makeText(this, "Video saved in gallery or $videoPath", Toast.LENGTH_LONG)
                    .show()

                // Send  notification of updated content.
                val values = ContentValues()
                values.put(MediaStore.Video.Media.TITLE, "Sceneform Video")
                values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
                values.put(MediaStore.Video.Media.DATA, videoPath)
                contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)
            }
        }
        buttonBack_ar2.setOnClickListener {
            refresh()
            finish()
        }
        fullscreen_ar2_button.setOnClickListener {
            fullscreen(isFullscreenPublic)
        }
        buttoninformation_ar2.setOnClickListener {
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.i_ar2_1, R.drawable.i_ar2_2,
                R.drawable.i_ar2_3, R.drawable.i_ar2_4)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 22)
        }
//        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
//            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
//                return@setOnTapArPlaneListener
//            }
//            if (check) {
//                val anchor = hitResult.createAnchor()
////                val session = arFragment.arSceneView.session
////                val position = floatArrayOf(0f, 0f, -0.75f)
////                val rotation = floatArrayOf(0f, 0f, 0f, 1f)
////                val anchor = session.createAnchor(Pose(position, rotation))
//                placeObject(arFragment, anchor, model)
//            } else {
//                Log.i("test123", "check is false")
////                andy[0].startWalking()
////                andy[1].startWalking()
//            }
//        }

//        countDown()

//        animate_kick_button.setOnClickListener { animateModel("Armature|ArmatureAction") }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        refresh()
        finish()
    }

    private fun fullscreen(isFullScreen: Boolean) {
        isFullscreenPublic =
            if (isFullScreen) {
                fullscreen_ar2_button.setImageResource(R.drawable.icon_fullscreen)
                refresh_button.visibility = View.VISIBLE
                buttoninformation_ar2.visibility = View.VISIBLE
                record_button.visibility = View.VISIBLE
                recyclerARRealWorld.visibility = View.VISIBLE
                false
            } else {
                fullscreen_ar2_button.setImageResource(R.drawable.icon_exit_fullscreen)
                refresh_button.visibility = View.INVISIBLE
                buttoninformation_ar2.visibility = View.INVISIBLE
                record_button.visibility = View.INVISIBLE
                recyclerARRealWorld.visibility = View.INVISIBLE
                true
            }
    }

    private fun refresh() {
        for (i in 0 until andy.size) {
            andy[i].loopTimerAnimationFishVarible.cancel()
            andy[i].loopTimerMovefishVarible.cancel()
            arFragment.arSceneView.scene.onRemoveChild(andy[i].demo)
        }
        renderable = ArrayList()
        andy = ArrayList()
    }

    fun getModel(model: Uri) {
        val session = arFragment.arSceneView.session
        val position = floatArrayOf(0f, -0.75f, -0.75f)
        val rotation = floatArrayOf(0f, 0f, 0f, 1f)
        val anchor = session!!.createAnchor(Pose(position, rotation))
        placeObject(arFragment, anchor, model)
    }

//    private fun animateModel(name: String) {
//        if (renderable.isNotEmpty()) {
//            for (i in 0 until renderable.size) {
//                animator[i].let { it ->
//                    if (it.isRunning) {
//                        it.end()
//                    }
//                }
//
//                renderable[i].let { modelRenderable ->
//                    val data = modelRenderable.getAnimationData(name)
//                    animator[i] = ModelAnimator(data, modelRenderable)
//                    animator[i].start()
//                }
//            }
//        }

//        animator2?.let { it ->
//            if (it.isRunning) {
//                it.end()
//            }
//        }
//        renderable[1].let { modelRenderable ->
//            val data = modelRenderable.getAnimationData(name)
//            animator2 = ModelAnimator(data, modelRenderable)
//            animator2?.start()
//        }
//    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
//        if (check) {
        ModelRenderable.builder()
            .setSource(fragment.context, model)
            .build()
            .thenAccept {
                renderable.add(it)
                addToScene(fragment, anchor, it)
            }
            .exceptionally {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(it.message).setTitle("Error")
                val dialog = builder.create()
                dialog.show()
                return@exceptionally null
            }
//                check = false                                                                             <---- deleted now
//        }
    }

    private fun addToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable) {

        val anchorNode = AnchorNode(anchor)
        val skeletonNode = SkeletonNode()
        skeletonNode.renderable = renderable

        val node = TransformableNode(fragment.transformationSystem)
//        node.worldRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 180f)

        node.worldPosition = Vector3(0f, 0.17f, 0f)
        node.worldScale = Vector3(1f, 1f, 1f)
        node.addChild(skeletonNode)
        node.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        andy.add(F74(node, this.renderable.lastOrNull()))

    }

    //    fun countDown() {
//        object : CountDownTimer(30000, 100) {
//            override fun onTick(millisUntilFinished: Long) { // Tick
//                if (animator == null || !animator!!.isRunning) {
//                    animateModel("Armature|ArmatureAction")
//                }
//            }
//
//            override fun onFinish() { // Finish
//                countDown()
//            }
//        }.start()
//    }

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