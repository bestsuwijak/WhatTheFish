package buu.informatics.s59160141.whatthefish.ar

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.CamcorderProfile
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import buu.informatics.s59160141.whatthefish.viewpager.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.SkeletonNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class ARDetail : AppCompatActivity() {

    lateinit var arFragment: ArFragment
    private lateinit var model: Uri
    private var renderable: ModelRenderable? = null
    private var animator: ModelAnimator? = null
    var check = true
    lateinit var number: String
    var modelNode: TransformableNode? = null

    //////////////////////Record screen//////////////////////
    private var videoRecorder: VideoRecorder? = null
    lateinit var timerObject:CountDownTimer
    var timer = 0
    var minute = ""
    var second = ""
    ////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        number = intent.getStringExtra("number")
        arFragment = sceneform_fragment as ArFragment
        model = Uri.parse(number.plus(".sfb"))

        arFragment.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, _: MotionEvent ->
            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                return@setOnTapArPlaneListener
            }
            val anchor = hitResult.createAnchor()
            if(check) {
                placeObject(arFragment, anchor, model)
            }else{
                arFragment.arSceneView.scene.onRemoveChild(modelNode)
                animator?.end()
                check = true
                placeObject(arFragment, anchor, model)
            }
        }

        countDown()
//        animate_kick_button.setOnClickListener { animateModel("Armature|ArmatureAction") }

        buttonBack_ar1.setOnClickListener{
            finish()
        }

        buttonInformation_ar1.setOnClickListener{
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.i_ar1_1, R.drawable.i_ar1_2, R.drawable.i_ar1_3, R.drawable.i_ar1_4)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 10)
        }

        buttonrefresh.setOnClickListener{
            refreshModel()
        }

        //////////////////////Record screen//////////////////////
        buttonrecord.setOnClickListener {
            if (videoRecorder == null) {
                videoRecorder = VideoRecorder()
                val orientation = resources.configuration.orientation
                videoRecorder!!.setVideoQuality(CamcorderProfile.QUALITY_2160P, orientation)
                videoRecorder!!.setSceneView(arFragment.arSceneView)
            }
            val isRecording: Boolean = videoRecorder!!.onToggleRecord()
            if (isRecording) {
                startRecord()
            } else {
                stopRecord()
            }
        }
        /////////////////////////////////////////////////////////

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
        if(check) {
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
            check = false
        }
    }

    private fun addToScene(fragment: ArFragment, anchor: Anchor, renderable: Renderable) {

        val anchorNode = AnchorNode(anchor)
        val skeletonNode = SkeletonNode()
        skeletonNode.renderable = renderable
        val node = TransformableNode(fragment.transformationSystem)
        node.localRotation = Quaternion.axisAngle(Vector3(0f, 1f, 0f), 180f)

        //floating
        node.localPosition = Vector3(0f,0.1f,-0.2f)

        node.addChild(skeletonNode)
        node.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        animateModel("Armature|ArmatureAction")
        node.getTranslationController().setEnabled(false);
        modelNode = node
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

    fun refreshModel(){
        if(modelNode != null) {
            arFragment.arSceneView.scene.onRemoveChild(modelNode)
            animator?.end()
            check = true
        }
    }

    override fun onPause() {
        super.onPause()
        if(arFragment.isResumed){
            arFragment.onPause()
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        val isRecording: Boolean = videoRecorder!!.onToggleRecord()
//        if (!isRecording) {
//            stopRecord()
//        }
//    }

    override fun onResume() {
        super.onResume()
        if(!arFragment.isResumed){
            arFragment.onResume()
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }

    //////////////////////Record screen//////////////////////

    fun timer() {
        timerObject = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) { // Tick
                timer++
                updateTextRecord()
            }

            override fun onFinish() { // Finish
                timerObject.start()
            }
        }
    }

    fun updateTextRecord(){
        if(timer/60 in 0..9){ minute = "0${timer/60}"
        }
        else{ minute = "${timer/60}"
        }
        if(timer%60 in 0..9){ second = "0${timer%60}"
        }
        else{ second = "${timer%60}"
        }

        timeRecord_ar1.text = ("${minute} : ${second}")
    }

    fun startRecord(){
//        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show()
        buttonrecord.setImageResource(R.drawable.rcd2)
        timeRecord_ar1.visibility = View.VISIBLE
        timer()
        timerObject.start()
    }

    fun stopRecord(){
//        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
        buttonrecord.setImageResource(R.drawable.rcd)
        timerObject.cancel()
        timeRecord_ar1.visibility = View.INVISIBLE
        timer = 0


        //Toast video path
        val videoPath: String = videoRecorder!!.getVideoPath()!!.absolutePath
        Toast.makeText(this, "Video saved in gallery or $videoPath", Toast.LENGTH_LONG).show()

        // Send  notification of updated content.
        val values = ContentValues()
        values.put(MediaStore.Video.Media.TITLE, "Sceneform Video")
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
        values.put(MediaStore.Video.Media.DATA, videoPath)
        contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)
    }
    ////////////////////////////////////////////////////////


}
