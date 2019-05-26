package test.tamhuynh.com.chappiebot.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import test.tamhuynh.com.chappiebot.R
import test.tamhuynh.com.chappiebot.databinding.NewfeedListItemBinding
import test.tamhuynh.com.chappiebot.service.model.Item
import test.tamhuynh.com.chappiebot.service.model.NewFeedModel
import test.tamhuynh.com.chappiebot.view.callback.ClickCallBack

class NewFeedAdapter(private val projectClickCallback: ClickCallBack?) :
    RecyclerView.Adapter<NewFeedAdapter.NewFeedViewHolder>() {

    private var projectList: List<Item>? = null

    fun setNewFeed(newFeedModel: NewFeedModel) {

        if (this.projectList == null) {
            this.projectList = newFeedModel.items

            notifyItemRangeInserted(0, projectList!!.size)

        } else {

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@NewFeedAdapter.projectList).size
                }

                override fun getNewListSize(): Int {
                    return projectList!!.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return requireNotNull(this@NewFeedAdapter.projectList)[oldItemPosition].document_id == projectList!![newItemPosition].document_id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = projectList!![newItemPosition]
                    val old = projectList!![oldItemPosition]

                    return project.document_id == old.document_id
                }
            })
            this.projectList = projectList

            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): NewFeedViewHolder {
        val binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.newfeed_list_item, parent,
                false
            ) as NewfeedListItemBinding

        binding.callback = projectClickCallback

        return NewFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewFeedViewHolder, position: Int) {
        holder.binding.item = requireNotNull(projectList)[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else requireNotNull(projectList).size
    }

    open class NewFeedViewHolder(val binding: NewfeedListItemBinding) : RecyclerView.ViewHolder(binding.root)
}