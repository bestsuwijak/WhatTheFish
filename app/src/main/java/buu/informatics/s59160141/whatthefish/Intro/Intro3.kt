@file:Suppress("DEPRECATION")

package buu.informatics.s59160141.whatthefish.Intro


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.databinding.FragmentIntro3Binding
import kotlinx.android.synthetic.main.fragment_intro3.*


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

        return binding.root
    }

    @SuppressLint("ResourceType")
    fun checkInternet(){
        val connectivityManager = this@Intro3.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected){
            goToMain()
        }else{
//            Log.i("test123", "device not conect internet")
            val builder = AlertDialog.Builder(this@Intro3.context)

            val title: TextView = TextView(context)
            title.setText(R.string.intro3_title)
            title.setPadding(30, 30, 0, 10)
            title.setTextSize(20F)
            title.setTextColor(resources.getColor(R.color.Titleblack))
            builder.setCustomTitle(title)

            builder.setMessage(R.string.intro3_message)
            builder.setOnCancelListener{
                if (isConnected){
                    goToMain()
                }
            }
            builder.setPositiveButton(R.string.intro3_button) { dialog, which ->
                val intent = Intent(Settings.ACTION_SETTINGS)
                startActivity(intent)
//                dialog.cancel()
            }
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("testIntro3", "resume")
        checkInternet()
    }

    fun goToMain(){
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) { // Tick
                loadText.visibility = View.VISIBLE
            }

            override fun onFinish() { // Finish
                viewModel.refreshDataFromRepository()
                findNavController().navigate(R.id.action_intro3_to_mainFragment)
            }
        }.start()
    }


}


