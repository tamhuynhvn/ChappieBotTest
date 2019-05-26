package test.tamhuynh.com.chappiebot.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import test.tamhuynh.com.chappiebot.service.model.DetailModel
import test.tamhuynh.com.chappiebot.service.repository.NewFeedRepository

class DetailViewModel(application: Application, mDocumentID: String) : AndroidViewModel(application) {

    val observableProject: LiveData<DetailModel> =
            NewFeedRepository
                    .instance
                    .getDetailModelDetails(mDocumentID)

    public var item = ObservableField<DetailModel>()

    fun setProject(item: DetailModel) {
        this.item.set(item)
    }

    class Factory(private val application: Application, private val documentID: String) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return DetailViewModel(application, documentID) as T
        }
    }
}
