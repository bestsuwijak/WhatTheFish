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
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.viewpager.MainViewPager
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
    lateinit var timerObject:CountDownTimer
    var timer = 0
    var minute = ""
    var second = ""
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
                startRecord()
            } else {
                stopRecord()
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
//
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

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
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

        timeRecord.text = ("${minute} : ${second}")
    }

    fun startRecord(){
//        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show()
        record_button.setImageResource(R.drawable.rcd2)
        timeRecord.visibility = View.VISIBLE
        timer()
        timerObject.start()
    }

    fun stopRecord(){
//        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
        record_button.setImageResource(R.drawable.rcd)
        timerObject.cancel()
        timeRecord.visibility = View.INVISIBLE
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

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )
        }
    }

//
}