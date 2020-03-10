package buu.informatics.s59160141.whatthefish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.adapters.DetailViewPageAdapter
import buu.informatics.s59160141.whatthefish.ar.ARDetail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*

class Detail : AppCompatActivity() {


    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    lateinit var viewPager: ViewPager
    lateinit var number: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_detail)

        //Click button
        buttonBack_detail.setOnClickListener{
            finish()
        }

        buttonInformation_detail.setOnClickListener{
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.popup_qr)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 5)
        }

        ar_qr2.setOnClickListener{
            val i = Intent(this, ARDetail::class.java)
            i.putExtra("number", number)
            startActivityForResult(i, 8)
        }

        //FindById
        val name = findViewById<TextView>(R.id.name_text)
        val thName = findViewById<TextView>(R.id.thName_text)
        val engName = findViewById<TextView>(R.id.engName_text)
        val sciName = findViewById<TextView>(R.id.sciName_text)
        val appearance = findViewById<TextView>(R.id.appearance_text)
        val habitat = findViewById<TextView>(R.id.habitat_text)
        val dissemination = findViewById<TextView>(R.id.dissemination_text)

        //Get value
        var images: ArrayList<String> = intent.getStringArrayListExtra("images")
        //drop last image (shadow image)
        val imageshadow: String = images.last()
        images.removeAt(images.lastIndex)
        val th_name: ArrayList<String> = intent.getStringArrayListExtra("thName")
        val eng_name: ArrayList<String> = intent.getStringArrayListExtra("engName")
        val sci_name: String = intent.getStringExtra("sciName")
        val app: String = intent.getStringExtra("appearance")
        val hab: String = intent.getStringExtra("habitat")
        val dis: String = intent.getStringExtra("dissemination")
        val foun: Boolean = intent.getBooleanExtra("foundNewFish", false)
        val icon: String = intent.getStringExtra("icon")
        val form: String = intent.getStringExtra("form")
        number = intent.getStringExtra("number").toLowerCase()


        //ViewPager
        viewPager = findViewById<View>(R.id.viewPager_detail) as ViewPager
        val adapter = DetailViewPageAdapter(this, images)
        viewPager.adapter = adapter

        var thname = ""
        var engname = ""
        for (i in 0 until th_name.size){
            if (i == (th_name.size - 1)){
                thname += th_name[i]
            }else{
                thname += "${th_name[i]}\n "
            }
        }
        for (i in 0 until eng_name.size){
            if (i == (eng_name.size - 1)){
                engname += eng_name[i]
            }else{
                engname += "${eng_name[i]}\n "
            }
        }

        name.text = th_name[0]
        thName.text = thname
        engName.text = engname
        sciName.text = sci_name
        appearance.text = app
        habitat.text = hab
        dissemination.text = dis

        if (foun && form == "qr"){
            newFish(th_name[0], icon, imageshadow)
        }

        checkAR()
    }
    fun newFish(nameth: String, icon: String, shadow: String) {
        detailLayout.visibility = View.GONE
        newFindingLayout.visibility = View.VISIBLE
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slide = AnimationUtils.loadAnimation(this, R.anim.slide_bot_to_top)
        val landingFish = AnimationUtils.loadAnimation(this, R.anim.zoom_out_and_fade_in)
        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        imageBackground.visibility = View.GONE
        fadeIn.duration = 1000L
        fadeOut.duration = 250L
        Glide.with(this)
            .load("http://thefishdev.buu.in.th${shadow}")
            .into(fishPic)
        viewModelScope.launch {
            delay(2000)
            imageBackground.visibility = View.VISIBLE
            imageBackground.startAnimation(fadeIn)
            delay(1000)
            imageBackground.startAnimation(fadeOut)
            delay(250)
            imageBackground.visibility = View.GONE
            effect_slide.visibility = View.VISIBLE
            effect_slide.startAnimation(slide)
            fishPic.startAnimation(landingFish)
            fishPic.visibility = View.VISIBLE
            delay(2000)
            Glide.with(this@Detail)
                .load("http://thefishdev.buu.in.th${icon}")
                .into(fishPic)
            textNameFish.text = "พบ ${nameth} แล้ว!!"
            cycleEffect.visibility = View.VISIBLE
            textNameFish.visibility = View.VISIBLE
            cycleEffect.startAnimation(zoomIn)
            delay(1000)
            cycleEffect.visibility = View.GONE
            delay(2000)
            effect_slide.visibility = View.GONE
            delay(1500)
            fishPic.visibility = View.GONE
            textNameFish.visibility = View.GONE
            newFindingLayout.visibility = View.GONE
            detailLayout.visibility = View.VISIBLE
        }
    }

    fun checkAR(){
        var list: ArrayList<String> = arrayListOf("f1", "f3", "f7", "f8", "f12" ,"f28" ,"f52" ,"f53" ,"f56" ,"f57"
            ,"f58" ,"f59" ,"f60" ,"f64" ,"f65" ,"f74" ,"f75" ,"f106" ,"f117" ,"f128" ,"f131" ,"f145" ,"f148" ,"f149")

        val row = list.find {it.startsWith(number)}
        if(row != number){
            ar_qr2.visibility = View.INVISIBLE
        }
    }
}
