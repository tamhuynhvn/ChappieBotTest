package test.tamhuynh.com.chappiebot.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.tamhuynh.com.chappiebot.R
import test.tamhuynh.com.chappiebot.databinding.FragmentProjectDetailsBinding
import test.tamhuynh.com.chappiebot.viewModel.DetailViewModel
import java.util.*

private const val KEY_ITEM_ID = "item_id"

class ProjectFragment : Fragment() {

    private var binding: FragmentProjectDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details,container,false)
        return requireNotNull(this.binding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        assert(arguments != null)
        val factory = DetailViewModel.Factory(
                Objects.requireNonNull<FragmentActivity>(activity).application, requireNotNull(arguments).getString(KEY_ITEM_ID)!!
        )
        val viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        requireNotNull(binding).detailViewModel = viewModel
        requireNotNull(binding).isLoading = true
        observeViewModel(viewModel)

    }

    private fun observeViewModel(viewModel: DetailViewModel) {
        viewModel.observableProject.observe(this, Observer { detail ->
            if (detail != null) {
                requireNotNull(binding).isLoading = false
                viewModel.setProject(detail)
            }
        })
    }

    companion object PUT {
        fun forProject(itemID: String): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()
            args.putString(KEY_ITEM_ID, itemID)
            fragment.arguments = args
            return fragment
        }
    }

}
