package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import buu.informatics.s59160141.whatthefish.Detail
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

        holder.nameFish.text = "${fish[position].thNames[0]}\n${fish[position].engNames[0]}"
        if (fish[position].foundFish) {
            Glide.with(this.context)
                .load("http://thefishdev.buu.in.th${fish[position].icon.name}")
                .into(holder.imageGrid)
        }else{
            holder.itemSmall.setBackgroundResource(R.drawable.gray_shape)
            Glide.with(this.context)
                .load("http://thefishdev.buu.in.th${fish[position].images.last().name}")
                .into(holder.imageGrid)
        }
        holder.itemID.setOnClickListener {
            if (!fish[position].foundFish) {
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
            }else{
                val thName = fish[position].thNames as ArrayList
                val engName = fish[position].engNames as ArrayList
                val sciName = fish[position].scienceName
                val appearance = fish[position].appearance
                val habitat = fish[position].habitat
                val dissemination = fish[position].dissemination

                var images: ArrayList<String> = ArrayList()
                if(fish[position].images.isNotEmpty()){
                    for (i in 0 until fish[position].images.size -1) {
                        images.add(fish[position].images[i].name)
                    }
                }

                val i = Intent(this.context, Detail::class.java)        //Go!!!
                i.putExtra("images", images)
                i.putExtra("thName", thName)
                i.putExtra("engName", engName)
                i.putExtra("sciName", sciName)
                i.putExtra("appearance", appearance)
                i.putExtra("habitat", habitat)
                i.putExtra("dissemination", dissemination)
                context.startActivity(i)
            }
        }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val imageGrid: ImageView = itemLayoutView.shadowIconFish
        val itemID: LinearLayout = itemLayoutView.itemID
        val itemSmall: LinearLayout = itemLayoutView.itemLayout
        val nameFish: TextView = itemLayoutView.nameFish
    }
}