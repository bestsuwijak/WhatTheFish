package buu.informatics.s59160141.whatthefish.main


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.MainActivity
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.ViewPageAdapter
import buu.informatics.s59160141.whatthefish.databinding.FragmentMainBinding
import buu.informatics.s59160141.whatthefish.qrcode.QRActivity
import kotlinx.android.synthetic.main.popup_main.view.*

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
                R.drawable.popupmain1,
                R.drawable.popupmain2,
                R.drawable.popupmain3,
                R.drawable.popupmain4)
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

            val i = Intent(context, QRActivity::class.java)
            startActivityForResult(i, 1)
//            val v:MainActivity = MainActivity()
//            v.startScan()
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


