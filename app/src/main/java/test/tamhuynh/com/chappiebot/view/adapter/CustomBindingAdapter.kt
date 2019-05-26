package test.tamhuynh.com.chappiebot.view.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import test.tamhuynh.com.chappiebot.service.model.Image
import test.tamhuynh.com.chappiebot.service.model.Section
import java.lang.StringBuilder


object CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("images")
    @JvmStatic
    fun loadImages(view: RecyclerView, images: List<Image>?) {
        if (images != null) {
            val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

            view.layoutManager = layoutManager
            val imgUrl = ArrayList<String>()
            for (i in 0 until images.size) {
                imgUrl.add(images[i].href)
            }
            if (images != null) {
                var adapter = ImageAdapter(view.context, imgUrl)
                adapter.setHasStableIds(true)
                view.adapter = adapter
            }
        }
    }

    @BindingAdapter("textContain")
    @JvmStatic
    fun loadContainText(view: TextView, sections: List<Section>?) {
        if (sections != null) {
            val stringBuilder = StringBuilder()
            for (i in 0 until sections.size) {
                if (sections[i].section_type == 1)
                    stringBuilder.append(sections[i].content.text+" ")
            }
            view.text = stringBuilder.toString()
        }
    }
}
