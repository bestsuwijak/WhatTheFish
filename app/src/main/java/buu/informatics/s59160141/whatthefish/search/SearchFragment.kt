package buu.informatics.s59160141.whatthefish.search


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.Intro.Intro3ViewModel
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.GithubUserAdapter
import buu.informatics.s59160141.whatthefish.database.getDatabase
import buu.informatics.s59160141.whatthefish.databinding.FragmentSearchBinding
import buu.informatics.s59160141.whatthefish.models.Fish
import buu.informatics.s59160141.whatthefish.repository.FishesRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), MainView {

    private val searchFragmentViewModel: SearchFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, SearchFragmentViewModel.Factory(activity.application, this))
            .get(SearchFragmentViewModel::class.java)
    }

    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search, container, false
        )

        binding.buttonBack.setOnClickListener{
            findNavController().navigate(R.id.action_searchFragment_to_mainFragment)
        }

        binding.listUsers.layoutManager = LinearLayoutManager(this.context)
        binding.listUsers.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?

        binding.searchUsers.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE
                || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) { //do what you want on the press of 'done'

                if(searchUsers.text.toString() == ""){
                    buttonCancel.setTextColor(Color.GRAY)//กดไม่ได้
                    listUsers.adapter = null//clear listView
                    showCountSearch.visibility = View.INVISIBLE//ซ่อน search count
                }else{
                    buttonCancel.setTextColor(Color.WHITE)//กดได้
//                    presenter.searchUser(searchUsers.text.toString())
                    // นำไปค้นหา
                    searchFragmentViewModel.multiSearch(searchUsers.text.toString())
//                    setAdapterData(searchFragmentViewModel.multiSearch(searchUsers.text.toString()))
                }
            }
            false
        }

        binding.buttonCancel.setOnClickListener{
            if (buttonCancel.currentTextColor == Color.WHITE){//สีขาวกดได้
                searchUsers.text.clear()//ช่องค้นหาว่า
                listUsers.adapter = null//clear listView
                showCountSearch.visibility = View.INVISIBLE//ซ่อน search count
                buttonCancel.setTextColor(Color.GRAY)//cancel สีเทา
            }
        }

        return binding.root
    }

    override fun setAdapterData(items: List<Fish>) {
//        Log.i("test123", "in setAdap searchFragment Aleart!!")
        listUsers.adapter = GithubUserAdapter(this, items)

        showCountSearch.visibility = View.VISIBLE
        showCountSearch.text = "พบ " +items.size +" ตัว"
    }

}
