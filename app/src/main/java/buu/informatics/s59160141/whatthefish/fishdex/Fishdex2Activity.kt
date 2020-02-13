package buu.informatics.s59160141.whatthefish.fishdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buu.informatics.s59160141.whatthefish.R
import buu.informatics.s59160141.whatthefish.adapters.GridViewAdapter
import buu.informatics.s59160141.whatthefish.databinding.ActivityFishdex2Binding
import buu.informatics.s59160141.whatthefish.models.*
import kotlinx.android.synthetic.main.activity_fishdex2.*

class Fishdex2Activity : AppCompatActivity(), fishDexView {

    private lateinit var binding: ActivityFishdex2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fishdex2)
        binding.gridFish.layoutManager = GridLayoutManager(this, 5)
        binding.gridFish.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
//        setContentView(R.layout.activity_fishdex2)

        setAdapterDataGrid(listOf(Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
            TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552985925657F51_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552985925657F51_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552985925657F51_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0),
            Fish(thNames = listOf("ttt"), engNames = listOf("sss"), scienceName = "tesdfah", appearance = "sfde", habitat = "asdfea", dissemination = "sesd", editedAt = "fsetgs", createdAt = "aesfa", textureImages = listOf(
                TextureImage("asfse", 1.0)), images = listOf(Image("/images/1552989795661F1_shadow.png",1.3)), model = Model("asfe", 1.5), icon = Icon("asfe", 1.2), number = "F0", status = 0)))

    }

    override fun setAdapterDataGrid(items: List<Fish>) {
        gridFish.adapter = GridViewAdapter(this, items)
    }
}
