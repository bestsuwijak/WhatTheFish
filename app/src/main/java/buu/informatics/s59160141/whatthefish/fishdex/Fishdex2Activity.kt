package buu.informatics.s59160141.whatthefish.fishdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import buu.informatics.s59160141.whatthefish.R

class Fishdex2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_fishdex2)
    }
}
