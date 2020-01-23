package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.models.Fishs
import buu.informatics.s59160141.whatthefish.search.SearchFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_list_item.view.*

class GithubUserAdapter(private val context: SearchFragment, private val fishs: List<Fishs>): RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserAdapter.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return fishs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var th_name = ""
        var eng_name = ""
        for (i in 0 until fishs[position].thNames.size){
            th_name += "${fishs[position].thNames[i]}, "
        }
        for (i in 0 until fishs[position].engNames.size){
            eng_name += "${fishs[position].engNames[i]}, "
        }
        holder.textThNames.text = th_name
        holder.textEngNames.text = eng_name
        holder.textScienceName.text = fishs[position].scienceName
        holder.avatar.setOnClickListener {

        }
        Glide.with(context).load("http://10.0.2.2${fishs[position].icon}").into(holder.avatar)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        val textThNames: TextView = itemLayoutView.textThNames
        val textEngNames: TextView = itemLayoutView.textEngNames
        val textScienceName: TextView = itemLayoutView.textScienceName
        val avatar: ImageView = itemLayoutView.iconFish

    }
}