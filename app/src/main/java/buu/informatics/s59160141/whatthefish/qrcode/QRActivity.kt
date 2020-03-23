package buu.informatics.s59160141.whatthefish.qrcode

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Vibrator
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import buu.informatics.s59160141.whatthefish.detail.Detail
import buu.informatics.s59160141.whatthefish.viewpager.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_qr.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Suppress("DEPRECATION")
class QRActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private val REQUES_CAMERA = 1
    private var scannerView: ZXingScannerView? = null
    private var txtResult: TextView? = null
    private val qRViewModel: QRViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, QRViewModel.Factory(activity.application))
            .get(QRViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_qr)

        buttonBack_qr.setOnClickListener {
            finish()
        }
        buttonInformation_qr.setOnClickListener {
            val images: ArrayList<Int> = arrayListOf(R.drawable.i_qr)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 3)
        }

        scannerView = findViewById(R.id.scanner)
        txtResult = findViewById(R.id.txtResult)

        if (!checkPermission()) {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            REQUES_CAMERA
        )
    }

    override fun onResume() {
        super.onResume()
        if (checkPermission()) {
            if (scannerView == null) {
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

    override fun handleResult(p0: Result?) {
        val result = p0?.text
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)
//        txtResult?.text = result
        scannerView?.setResultHandler(this)
        scannerView?.startCamera()

        if (!result.isNullOrEmpty()) {
            scannerView?.stopCamera()

            var list: ArrayList<String> = arrayListOf("f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12", "f13", "f14", "f15", "f16", "f17", "f18", "f19", "f20"
                , "f21", "f22", "f23", "f24", "f25", "f26", "f27", "f28", "f29", "f30", "f31", "f32", "f33", "f34", "f35", "f36", "f37", "f38", "f39", "f40"
                , "f41", "f42", "f43", "f44", "f45", "f46", "f47", "f48", "f49", "f50", "f51", "f52", "f53", "f54", "f55", "f56", "f57", "f58", "f59", "f60"
                , "f61", "f62", "f63", "f64", "f65", "f66", "f67", "f68", "f69", "f70", "f71", "f72", "f73", "f74", "f75", "f76", "f77", "f78", "f79", "f80"
                , "f81", "f82", "f83", "f84", "f85", "f86", "f87", "f88", "f89", "f90", "f91", "f92", "f93", "f94", "f95", "f96", "f97", "f98", "f99", "f100"
                , "f101", "f102", "f103", "f104", "f105", "f106", "f107", "f108", "f109", "f110", "f111", "f112", "f113", "f114", "f115", "f116", "f117", "f118", "f119", "f120"
                , "f121", "f122", "f123", "f124", "f125", "f126", "f127", "f128", "f129", "f130", "f131", "f132", "f133", "f134", "f135", "f136", "f137", "f138", "f139", "f140"
                , "f141", "f142", "f143", "f144", "f145", "f146", "f147", "f148", "f149", "f150")
            val row = list.find {it.startsWith(result.toLowerCase())}

            if (row == result.toLowerCase()) {
                val fish = qRViewModel.searchQR(result)

                val thName = fish.thNames as ArrayList
                val engName = fish.engNames as ArrayList
                val sciName = fish.scienceName
                val appearance = fish.appearance
                val habitat = fish.habitat
                val dissemination = fish.dissemination
                var foundNewFish = false
                if (!fish.foundFish) {
                    val current = LocalDateTime.now()

                    val formatterYear = DateTimeFormatter.ofPattern("yyyy")
                    val formatterMonth = DateTimeFormatter.ofPattern("MM")
                    val formatterDay = DateTimeFormatter.ofPattern("dd")
                    val formatterTime = DateTimeFormatter.ofPattern("HH:mm")

                    var years = current.format(formatterYear).toInt()
                    var month = current.format(formatterMonth).toString()
                    var day = current.format(formatterDay).toString()
                    var time = current.format(formatterTime).toString()

                    years += 543
                    var montTh = when (month) {
                        "01" -> "มกราคม"
                        "02" -> "กุมภาพันธ์"
                        "03" -> "มีนาคม"
                        "04" -> "เมษายน"
                        "05" -> "พฤษภาคม"
                        "06" -> "มิถุนายน"
                        "07" -> "กรกฎาคม"
                        "08" -> "สิงหาคม"
                        "09" -> "กันยายน"
                        "10" -> "ตุลาคม"
                        "11" -> "พฤศจิกายน"
                        "12" -> "ธันวาคม"
                        else -> "ไม่มี"
                    }

                    val stringDate = "$day $montTh $years"
                    qRViewModel.updateFoundFish(result, stringDate, time)
                    foundNewFish = true
                }

                var images: ArrayList<String> = ArrayList()
                if (fish.images.isNotEmpty()) {
                    for (element in fish.images) {
                        images.add(element.name)
                    }
                }

                val i = Intent(this, Detail::class.java)
                i.putExtra("images", images)
                i.putExtra("thName", thName)
                i.putExtra("engName", engName)
                i.putExtra("sciName", sciName)
                i.putExtra("appearance", appearance)
                i.putExtra("habitat", habitat)
                i.putExtra("dissemination", dissemination)
                i.putExtra("foundNewFish", foundNewFish)
                i.putExtra("icon", fish.icon.name)
                i.putExtra("form", "qr")
                i.putExtra("number", fish.number)
                this.startActivityForResult(i, 7)
            }else{
                Toast.makeText(this, R.string.alert_qrcode, Toast.LENGTH_SHORT).show()
                val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator.vibrate(1000)
                scannerView?.startCamera()
            }

        }
    }


}
