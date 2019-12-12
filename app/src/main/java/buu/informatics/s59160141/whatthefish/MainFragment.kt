package buu.informatics.s59160141.whatthefish


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.databinding.FragmentMainBinding
import kotlinx.android.synthetic.*
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
            showPopup()
        }

        return binding.root
    }

    private fun showPopup() {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.popup_main, null)
        val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mDialogView.buttonClose.setOnClickListener{
            mAlertDialog.dismiss()
        }
    }

}


