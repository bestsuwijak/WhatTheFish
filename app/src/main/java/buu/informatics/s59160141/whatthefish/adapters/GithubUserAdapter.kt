package buu.informatics.s59160141.whatthefish.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.models.User
import buu.informatics.s59160141.whatthefish.search.SearchFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_list_item.view.*

class GithubUserAdapter(private val context: SearchFragment, private val users: List<User>): RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserAdapter.ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, null)
        itemLayoutView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textId.text = users[position].id.toString()
        holder.textUsername.text = users[position].login
        holder.buttonViewProfile.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(users[position].url))
            context.startActivity(browserIntent)
        }
        Glide.with(context).load(users[position].avatar_url).into(holder.avatar)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        val textId: TextView = itemLayoutView.textId
        val textUsername: TextView = itemLayoutView.textUsername
        val avatar: ImageView = itemLayoutView.avatar
        val buttonViewProfile: Button = itemLayoutView.buttonViewProfile

    }
}