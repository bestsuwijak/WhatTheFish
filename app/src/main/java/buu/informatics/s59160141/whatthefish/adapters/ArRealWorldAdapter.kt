package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import kotlinx.android.synthetic.main.ar_real_world_fish_item.view.*

class ArRealWorldAdapter(private val context: Context, private val fishAR: List<String>): RecyclerView.Adapter<ArRealWorldAdapter.ViewHolder>(){

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArRealWorldAdapter.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.ar_real_world_fish_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return fishAR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgARRealWorld.setImageResource(R.drawable.f1)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imgARRealWorld: ImageView = itemLayoutView.arRealworldFishImg
    }
}