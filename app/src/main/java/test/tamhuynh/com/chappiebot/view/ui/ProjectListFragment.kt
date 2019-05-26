package test.tamhuynh.com.chappiebot.view.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.tamhuynh.com.chappiebot.R
import test.tamhuynh.com.chappiebot.databinding.FragmentProjectListBinding
import test.tamhuynh.com.chappiebot.service.model.Item
import test.tamhuynh.com.chappiebot.view.adapter.NewFeedAdapter
import test.tamhuynh.com.chappiebot.view.callback.ClickCallBack
import test.tamhuynh.com.chappiebot.viewModel.NewFeedViewModel


const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ProjectListFragment : Fragment() {

    private var projectAdapter: NewFeedAdapter? = null
    private var binding: FragmentProjectListBinding? = null

    private val projectClickCallback = object : ClickCallBack {
        override fun onClick(item: Item) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(item)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        projectAdapter = NewFeedAdapter(projectClickCallback)
        requireNotNull(binding).projectList.adapter = projectAdapter
        requireNotNull(binding).isLoading = true
        return requireNotNull(binding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(NewFeedViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: NewFeedViewModel) {
        viewModel.projectListObservable.observe(this, Observer { newFeedModel ->
            if (newFeedModel != null) {
                requireNotNull(binding).isLoading = false
                projectAdapter!!.setNewFeed(newFeedModel)
            }
        })
    }
}
