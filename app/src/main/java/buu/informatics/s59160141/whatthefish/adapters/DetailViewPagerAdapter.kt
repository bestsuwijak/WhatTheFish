package buu.informatics.s59160141.whatthefish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import buu.informatics.s59160141.whatthefish.R
import com.bumptech.glide.Glide

class DetailViewPageAdapter(private val context: Context, image:List<String>) : PagerAdapter(){
    private var layoutInflater: LayoutInflater? = null
    private val images = image

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.slider_main, null)
        val image = view.findViewById<View>(R.id.image_view) as ImageView
        Glide.with(context).load("http://thefishdev.buu.in.th${images[position]}").into(image)
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)


    }

}