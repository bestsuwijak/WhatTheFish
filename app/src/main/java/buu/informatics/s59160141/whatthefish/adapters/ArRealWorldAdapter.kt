package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.ar.ARRealWorld
import kotlinx.android.synthetic.main.ar_real_world_fish_item.view.*

class ArRealWorldAdapter(val context: Context, private val fishAR: ArrayList<String>, var model: ARRealWorld): RecyclerView.Adapter<ArRealWorldAdapter.ViewHolder>(){

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
        holder.imgARRealWorld.setImageResource(context.resources.getIdentifier(fishAR[position].toLowerCase(), "drawable", context.packageName))
        holder.imgARRealWorld.setOnClickListener{
            model.getModel(Uri.parse(fishAR[position].plus(".sfb").toLowerCase()))
        }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imgARRealWorld: ImageView = itemLayoutView.arRealworldFishImg
    }
}