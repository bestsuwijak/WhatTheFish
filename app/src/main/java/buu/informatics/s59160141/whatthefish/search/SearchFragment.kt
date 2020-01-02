package buu.informatics.s59160141.whatthefish.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.GithubUserAdapter
import buu.informatics.s59160141.whatthefish.databinding.FragmentSearchBinding
import buu.informatics.s59160141.whatthefish.models.User
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), MainView {

    val presenter: MainPresenter = MainPresenter(this)

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
        binding.listUsers.itemAnimator = DefaultItemAnimator()

        binding.searchUsers.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                presenter.searchUser(text)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return binding.root
    }

    override fun setAdapterData(items: List<User>) {
        listUsers.adapter = GithubUserAdapter(this, items)
    }
}
