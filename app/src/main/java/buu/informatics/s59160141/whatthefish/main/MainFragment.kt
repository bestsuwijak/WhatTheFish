package buu.informatics.s59160141.whatthefish.main


import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.Detail
import buu.informatics.s59160141.whatthefish.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.databinding.FragmentMainBinding
import buu.informatics.s59160141.whatthefish.qrcode.QRActivity

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    internal lateinit var viewPager: ViewPager
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,container,false
        )

        binding.buttonInformation.setOnClickListener{
//            showPopup()
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.popupmain1, R.drawable.popupmain2,
                R.drawable.popupmain3, R.drawable.popupmain4)
            val i = Intent(context, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 2)
        }

        binding.buttonSearch.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }

        binding.buttonDetect.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_detecFragment)
        }

        binding.buttonQrcode.setOnClickListener{
//            findNavController().navigate(R.id.action_mainFragment_to_QRCodeFragment)
//            findNavController().navigate(R.id.action_mainFragment_to_QRActivity)

//            val v:MainActivity = MainActivity()
//            v.startScan()

            val i = Intent(context, QRActivity::class.java)
            startActivityForResult(i, 1)

            //----------------------------------------------------------ยืมทำ search detail
//            val images:ArrayList<Int> = arrayListOf(
//                R.drawable.popupmain1, R.drawable.popupmain2,
//                R.drawable.popupmain3, R.drawable.popupmain4)
//            val i = Intent(context, Detail::class.java)
//            i.putExtra("images", images)
//            startActivityForResult(i, 1)
            //----------------------------------------------------------
        }

        binding.buttonCollect.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_fishDexFragment)
        }

        return binding.root
    }

    private fun showPopup() {
//        val v = layoutInflater.inflate(R.layout.activity_main_view_pager, null)
//        viewPager = v.findViewById<View>(R.id.viewPager) as ViewPager
//        val adapter = ViewPageAdapter(v.context)
//        viewPager.adapter = adapter
//
//        val mDialogView = LayoutInflater.from(context).inflate(R.layout.activity_main_view_pager, null)
//        val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
//        val mAlertDialog = mBuilder.show()
//
//        mDialogView.buttonClose.setOnClickListener{
//            mAlertDialog.dismiss()
//        }
//
    }

}


