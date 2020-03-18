package buu.informatics.s59160141.whatthefish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.adapters.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main_view_pager.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainViewPager() : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main_view_pager)

        val images: List<Int> = intent.getIntegerArrayListExtra("images")

        Log.i("popup", "inMainViewPager")

//        val v = layoutInflater.inflate(R.layout.activity_main_view_pager, null)
        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter =
            ViewPageAdapter(
                this,
                images
            )
        viewPager.adapter = adapter

        buttonClose.setOnClickListener{
            finish()
        }
    }
}
