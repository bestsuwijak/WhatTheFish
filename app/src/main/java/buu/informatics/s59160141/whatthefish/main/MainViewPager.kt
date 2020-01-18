package buu.informatics.s59160141.whatthefish.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.app.DialogCompat
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main_view_pager.*
import java.util.ArrayList

class MainViewPager() : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main_view_pager)

//        val images = arrayOf(
//            R.drawable.popupmain1,
//            R.drawable.popupmain2,
//            R.drawable.popupmain3,
//            R.drawable.popupmain4)
        val images: ArrayList<Int> = intent.getIntegerArrayListExtra("images")

        Log.i("popup", "inMainViewPager")

//        val v = layoutInflater.inflate(R.layout.activity_main_view_pager, null)
        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter = ViewPageAdapter(this,images)
        viewPager.adapter = adapter

        buttonClose.setOnClickListener{
            finish()
        }
    }
}
