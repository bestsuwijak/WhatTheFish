package buu.informatics.s59160141.whatthefish.fishdex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import buu.informatics.s59160141.whatthefish.R
import kotlinx.android.synthetic.main.activity_fishdex1.*

class FishDex1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_fishdex1)

        buttonBack_fishdex1.setOnClickListener(){
            finish()
        }

        buttonFishdex.setOnClickListener{
            val i = Intent(this, Fishdex2Activity::class.java)
            startActivityForResult(i, 7)
        }
    }
}
