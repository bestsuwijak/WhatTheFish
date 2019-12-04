package buu.informatics.s59160141.whatthefish


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160141.whatthefish.databinding.FragmentIntro3Binding

/**
 * A simple [Fragment] subclass.
 */
class Intro3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentIntro3Binding>(
            inflater,
            R.layout.fragment_intro3,container,false
        )

        return binding.root
    }


}
