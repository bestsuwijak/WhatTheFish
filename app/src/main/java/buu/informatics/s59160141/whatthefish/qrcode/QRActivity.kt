package buu.informatics.s59160141.whatthefish.qrcode

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Vibrator
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import buu.informatics.s59160141.whatthefish.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_qr.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QRActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val REQUES_CAMERA = 1
    private var scannerView :ZXingScannerView? = null
    private  var txtResult : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_qr)

        buttonBack_qr.setOnClickListener{
            finish()
        }
        buttonInformation_qr.setOnClickListener{
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.popup_qr)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 3)
        }

        scannerView = findViewById(R.id.scanner)
        txtResult = findViewById(R.id.txtResult)

        if(!checkPermission()){
            requestPermission()
        }
    }

    private fun checkPermission():Boolean{
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), REQUES_CAMERA)
    }

    override fun onResume() {
        super.onResume()
        if(checkPermission()){
            if(scannerView == null){
                scannerView = findViewById(R.id.scanner)
                setContentView(scannerView)
            }
            scannerView?.setResultHandler(this)
            scannerView?.startCamera()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView?.stopCamera()
    }

    override fun handleResult(p0: Result?){
        val result = p0?.text
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)
        txtResult?.text = result
        scannerView?.setResultHandler(this)
        scannerView?.startCamera()

        val returnIntent = Intent()
        returnIntent.putExtra("result", result)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()

//        val i = Intent(context, QRActivity::class.java)
//        startActivityForResult(i, 1)

    }

}
