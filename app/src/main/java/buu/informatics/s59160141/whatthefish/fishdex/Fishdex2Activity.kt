package buu.informatics.s59160141.whatthefish.fishdex

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.viewpager.MainViewPager
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.GridViewAdapter
import buu.informatics.s59160141.whatthefish.ar.ARRealWorld
import buu.informatics.s59160141.whatthefish.databinding.ActivityFishdex2Binding
import buu.informatics.s59160141.whatthefish.models.*
import kotlinx.android.synthetic.main.activity_fishdex2.*

class Fishdex2Activity : AppCompatActivity(), fishDex2View {

    private lateinit var binding: ActivityFishdex2Binding
    private var listArVisibled = ArrayList<String>()
    private val listfig = arrayListOf(
        "f1",
        "f3",
        "f7",
        "f8",
        "f12",
        "f28",
        "f52",
        "f53",
        "f56",
        "f57"
        ,
        "f58",
        "f59",
        "f60",
        "f64",
        "f65",
        "f74",
        "f75",
        "f106",
        "f117",
        "f128",
        "f131",
        "f145",
        "f148",
        "f149"
    )
    private val viewModel: FishDex2ViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, FishDex2ViewModel.Factory(activity.application, this))
            .get(FishDex2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fishdex2)
        binding.gridFish.layoutManager = GridLayoutManager(this, 5)
        binding.gridFish.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        //set adapter and get fish list
        viewModel.getFishes()

        buttonBack_fishdex2.setOnClickListener {
            finish()
        }
        ar2button.setOnClickListener {
            val i = Intent(this, ARRealWorld::class.java)
            i.putExtra("number", listArVisibled)
            startActivityForResult(i, 9)
        }
        buttonInformation_fishdex2.setOnClickListener {
            val images:ArrayList<Int> = arrayListOf(
                R.drawable.i_fish_dex_1, R.drawable.i_fish_dex_2,
                R.drawable.i_fish_dex_3, R.drawable.i_fish_dex_4)
            val i = Intent(this, MainViewPager::class.java)
            i.putExtra("images", images)
            startActivityForResult(i, 21)
        }
    }

    override fun setAdapterDataGrid(items: List<Fish>) {
        for (element in items) {
            if (listfig.indexOf(element.number.toLowerCase()) != -1 && element.foundFish) {
                listArVisibled.add(element.number)
            }
        }

        gridFish.adapter = GridViewAdapter(this, items)

        //set text count found fish
        val countFoundFish = items.count { it.foundFish }
        val sizeFish = items.size
        val spannable = SpannableStringBuilder("พบ  ตัว ทั้งหมด ${sizeFish} ตัว")
        spannable.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        spannable.insert(3, countFoundFish.toString())
        countFoundFishTextView.text = spannable
    }
}
