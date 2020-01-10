package buu.informatics.s59160141.whatthefish

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import buu.informatics.s59160141.whatthefish.qrcode.QRActivity


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

    }

    fun startScan(){
//        val i = Intent(this@MainActivity, QRActivity::class.java)
//        this@MainActivity.startActivityForResult(i, 1)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                val result = data?.getStringExtra("result")
//                Toast.makeText(applicationContext,result, Toast.LENGTH_SHORT).show()
                Log.i("onActivityResult", data?.getStringExtra("result"))
                Log.i("onActivityResult", "requestCode =" +requestCode)
            }
            else if (resultCode == Activity.RESULT_CANCELED) { //Write your code if there's no result
            }
            else{
                Log.i("onActivityResult","not 1")
            }
//        }else{
//            val result = data?.getStringExtra("result")
//            Toast.makeText(applicationContext,result, Toast.LENGTH_SHORT).show()
//            Log.i("onActivityResult", "else")
//        }
    }


}
