package buu.informatics.s59160141.whatthefish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.adapters.DetailViewPageAdapter

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
        val images: ArrayList<String> = intent.getStringArrayListExtra("images")
        val th_name: ArrayList<String> = intent.getStringArrayListExtra("thName")
        val eng_name: ArrayList<String> = intent.getStringArrayListExtra("engName")
        val sci_name: String = intent.getStringExtra("sciName")
        val app: String = intent.getStringExtra("appearance")
        val hab: String = intent.getStringExtra("habitat")
        val dis: String = intent.getStringExtra("dissemination")

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


    }
}
