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


        val images: ArrayList<Int> = intent.getIntegerArrayListExtra("images")
//        val v = layoutInflater.inflate(R.layout.activity_main_view_pager, null)
        viewPager = findViewById<View>(R.id.viewPager_detail) as ViewPager
        val adapter = ViewPageAdapter(this,images)
        viewPager.adapter = adapter


        val thName = findViewById<TextView>(R.id.thName_text)
        val engName = findViewById<TextView>(R.id.engName_text)
        val sciName = findViewById<TextView>(R.id.sciName_text)
        val appearance = findViewById<TextView>(R.id.appearance_text)
        val habitat = findViewById<TextView>(R.id.habitat_text)
        val dissemination = findViewById<TextView>(R.id.dissemination_text)
        thName.text = "What \nthe \nfish"

    }
}
