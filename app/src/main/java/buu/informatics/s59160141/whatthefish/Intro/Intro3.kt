package buu.informatics.s59160141.whatthefish.Intro


import android.app.Application
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.database.getDatabase
import buu.informatics.s59160141.whatthefish.databinding.FragmentIntro3Binding
import buu.informatics.s59160141.whatthefish.repository.FishesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class Intro3 : Fragment() {

    private val viewModel: Intro3ViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, Intro3ViewModel.Factory(activity.application))
            .get(Intro3ViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentIntro3Binding>(
            inflater,
            R.layout.fragment_intro3, container, false
        )
        countDown()
        return binding.root
    }

    fun countDown() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) { // Tick

            }

            override fun onFinish() { // Finish
                viewModel.refreshDataFromRepository()
                findNavController().navigate(R.id.action_intro3_to_mainFragment)
            }
        }.start()
    }


}
