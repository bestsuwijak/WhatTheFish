package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.models.Fish
import buu.informatics.s59160141.whatthefish.search.SearchFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_list_item.view.*

class GithubUserAdapter(private val context: SearchFragment, private val fish: List<Fish>): RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserAdapter.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return fish.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var th_name = ""
        var eng_name = ""
        for (i in 0 until fish[position].thNames.size){
            if (i == (fish[position].thNames.size - 1)){
                th_name += fish[position].thNames[i]
            }else{
                th_name += "${fish[position].thNames[i]}, "
            }
        }
        for (i in 0 until fish[position].engNames.size){
            if (i == (fish[position].engNames.size - 1)){
                eng_name += fish[position].engNames[i]
            }else{
                eng_name += "${fish[position].engNames[i]}, "
            }
        }
        holder.textThNames.text = th_name
        holder.textEngNames.text = eng_name
        holder.textScienceName.text = fish[position].scienceName
        holder.itemID.setOnClickListener {
            Log.i("test123", "Navigate Here. ${fish[position].scienceName}")
        }
        Glide.with(context).load("http://thefishdev.buu.in.th${fish[position].icon.name}").into(holder.avatar)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        val textThNames: TextView = itemLayoutView.textThNames
        val textEngNames: TextView = itemLayoutView.textEngNames
        val textScienceName: TextView = itemLayoutView.textScienceName
        val avatar: ImageView = itemLayoutView.iconFish
        val itemID: LinearLayout = itemLayoutView.itemID

    }
}