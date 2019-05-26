package test.tamhuynh.com.chappiebot.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import test.tamhuynh.com.chappiebot.service.model.NewFeedModel
import test.tamhuynh.com.chappiebot.service.repository.NewFeedRepository

class NewFeedViewModel(application: Application) : AndroidViewModel(application) {

    var projectListObservable: LiveData<NewFeedModel> =
            NewFeedRepository
                    .instance
                    .getNewFeedList()

}
