package buu.informatics.s59160141.whatthefish.fishdex


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import buu.informatics.s59160141.whatthefish.R

/**
 * A simple [Fragment] subclass.
 */
class FishDexFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fish_dex, container, false)
    }


}
