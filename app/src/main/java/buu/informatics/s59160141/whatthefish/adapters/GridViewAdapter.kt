package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class GridViewAdapter(private val context: Context, private val fish: List<Fish>): RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return fish.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(this.context).load("http://thefishdev.buu.in.th${fish[position].images.last().name}").into(holder.imageGrid)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imageGrid: ImageView = itemLayoutView.shadowIconFish
        val itemID: LinearLayout = itemLayoutView.itemID
    }
}