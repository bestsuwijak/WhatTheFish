package buu.informatics.s59160141.whatthefish.fishdex

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.models.Fish
import kotlinx.android.synthetic.main.activity_fishdex1.*

class FishDex1Activity : AppCompatActivity(), fishDex1View {

    private val viewModel: FishDex1ViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, FishDex1ViewModel.Factory(activity.application, this))
            .get(FishDex1ViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_fishdex1)
        viewModel.getFishes()

        buttonBack_fishdex1.setOnClickListener{
            finish()
        }

        buttonFishdex.setOnClickListener{
            val i = Intent(this, Fishdex2Activity::class.java)
            startActivityForResult(i, 7)
        }
    }

    override fun setCountFoundFishes(items: List<Fish>) {
        //set text count found fish
        val countFoundFish = items.count { it.foundFish }
        val sizeFish = items.size
        val spannable = SpannableStringBuilder("พบ  ตัว ทั้งหมด ${sizeFish} ตัว")
        spannable.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            0, 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        spannable.insert(3, countFoundFish.toString())
        countFoundFishTextView1.text = spannable
    }
}
