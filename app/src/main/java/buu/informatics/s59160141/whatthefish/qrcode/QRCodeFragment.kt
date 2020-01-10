package buu.informatics.s59160141.whatthefish.qrcode


import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160141.whatthefish.MainActivity
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.databinding.FragmentQrcodeBinding
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

/**
 * A simple [Fragment] subclass.
 */
class QRCodeFragment : Fragment(){

    lateinit var binding: FragmentQrcodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQrcodeBinding>(inflater, R.layout.fragment_qrcode, container, false)

//        val v:MainActivity = MainActivity()
//        v.startScan()

        return binding.root
    }


}
