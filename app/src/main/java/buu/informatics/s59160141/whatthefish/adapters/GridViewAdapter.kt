package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.models.Fish
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.grid_view_item.view.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

class GridViewAdapter(private val context: Context, private val fish: List<Fish>) :
    RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return fish.size
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(this.context)
            .load("http://thefishdev.buu.in.th${fish[position].images.last().name}")
            .into(holder.imageGrid)
        holder.nameFish.text = "${fish[position].thNames[0]}\n${fish[position].engNames[0]}"
        holder.itemID.setOnClickListener {
            viewModelScope.launch {
                val animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
                if (it.shadowIconFish.visibility == View.VISIBLE) {
                    it.shadowIconFish.visibility = View.GONE
                    it.startAnimation(animation)
                    delay(200)
                    it.itemLayout.setBackgroundResource(R.drawable.blue_shape)
                    it.nameFish.visibility = View.VISIBLE
                } else {
                    it.nameFish.visibility = View.GONE
                    it.startAnimation(animation)
                    delay(200)
                    it.itemLayout.setBackgroundResource(R.drawable.gray_shape)
                    it.shadowIconFish.visibility = View.VISIBLE
                }

            }
        }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imageGrid: ImageView = itemLayoutView.shadowIconFish
        val itemID: LinearLayout = itemLayoutView.itemID
        val nameFish: TextView = itemLayoutView.nameFish
    }
}