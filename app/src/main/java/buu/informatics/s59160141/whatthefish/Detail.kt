package buu.informatics.s59160141.whatthefish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class Detail : AppCompatActivity() {


    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_detail)

        //FindById
        val name = findViewById<TextView>(R.id.name_text)
        val thName = findViewById<TextView>(R.id.thName_text)
        val engName = findViewById<TextView>(R.id.engName_text)
        val sciName = findViewById<TextView>(R.id.sciName_text)
        val appearance = findViewById<TextView>(R.id.appearance_text)
        val habitat = findViewById<TextView>(R.id.habitat_text)
        val dissemination = findViewById<TextView>(R.id.dissemination_text)

        //Get value
        val images: ArrayList<Int> = intent.getIntegerArrayListExtra("images")
        val th_name: ArrayList<String> = intent.getStringArrayListExtra("thName")
        val eng_name: ArrayList<String> = intent.getStringArrayListExtra("engName")
        val sci_name: String = intent.getStringExtra("sciName")
        val app: String = intent.getStringExtra("appearance")
        val hab: String = intent.getStringExtra("habitat")
        val dis: String = intent.getStringExtra("dissemination")

        //ViewPager
        viewPager = findViewById<View>(R.id.viewPager_detail) as ViewPager
        val adapter = ViewPageAdapter(this,images)
        viewPager.adapter = adapter


//        var th_name = ""
//        var eng_name = ""
//        for (i in 0 until fish[position].thNames.size){
//            if (i == (fish[position].thNames.size - 1)){
//                th_name += fish[position].thNames[i]
//            }else{
//                th_name += "${fish[position].thNames[i]}, "
//            }
//        }
//        for (i in 0 until fish[position].engNames.size){
//            if (i == (fish[position].engNames.size - 1)){
//                eng_name += fish[position].engNames[i]
//            }else{
//                eng_name += "${fish[position].engNames[i]}, "
//            }
//        }


        name.text = th_name.toString()
        thName.text = th_name.toString()
        engName.text = eng_name.toString()
        sciName.text = sci_name
        appearance.text = app
        habitat.text = hab
        dissemination.text = dis


    }
}
