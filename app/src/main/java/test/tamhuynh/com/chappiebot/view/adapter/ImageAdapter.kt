package test.tamhuynh.com.chappiebot.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import test.tamhuynh.com.chappiebot.R
import test.tamhuynh.com.chappiebot.service.model.Image

class ImageAdapter(private val context: Context?, private val imageList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ImageViewHolder {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = inflater.inflate(R.layout.many_image_views, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (context != null) {
            Glide.with(context).load(imageList[position]).into(holder.view as ImageView)
        }
    }

    override fun getItemCount(): Int {
        if (imageList != null) {
            return imageList.size
        }
        return 0
    }

    open class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}