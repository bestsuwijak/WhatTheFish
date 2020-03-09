package buu.informatics.s59160141.whatthefish.fishdex

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
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.GridViewAdapter
import buu.informatics.s59160141.whatthefish.databinding.ActivityFishdex2Binding
import buu.informatics.s59160141.whatthefish.models.*
import kotlinx.android.synthetic.main.activity_fishdex2.*

class Fishdex2Activity : AppCompatActivity(), fishDexView {

    private lateinit var binding: ActivityFishdex2Binding
    private val viewModel: FishDexViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, FishDexViewModel.Factory(activity.application, this))
            .get(FishDexViewModel::class.java)
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
        viewModel.getFishes()

        buttonBack_fishdex2.setOnClickListener{
            finish()
        }
        imageView4.setOnClickListener{
//            val i = Intent(this, ARActivity::class.java)
//            startActivityForResult(i, 8)
        }
    }

    override fun setAdapterDataGrid(items: List<Fish>) {
        gridFish.adapter = GridViewAdapter(this, items)

        //set text count found fish
        val countFoundFish = items.count { it.foundFish }
        val sizeFish = items.size
        val spannable = SpannableStringBuilder("พบ  ตัว ทั้งหมด ${sizeFish} ตัว")
        spannable.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        spannable.insert(3, countFoundFish.toString())
        countFoundFishTextView.text = spannable
    }
}
