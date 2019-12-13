package buu.informatics.s59160141.whatthefish.Intro


import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.whatthefish.R

/**
 * A simple [Fragment] subclass.
 */
class Intro2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        countDown()
        return inflater.inflate(R.layout.fragment_intro2, container, false)
    }

    fun countDown(){
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) { // Tick

            }
            override fun onFinish() { // Finish
                findNavController().navigate(R.id.action_intro2_to_intro3)
            }
        }.start()
    }


}
