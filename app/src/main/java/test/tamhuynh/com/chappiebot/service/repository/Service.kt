package test.tamhuynh.com.chappiebot.service.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import test.tamhuynh.com.chappiebot.service.model.DetailModel
import test.tamhuynh.com.chappiebot.service.model.NewFeedModel

const val HTTPS_API_DROPBOX_URL = "https://www.dropbox.com/"

internal interface Service {

    @GET("s/fy6ny7syutxl1yd/newsfeed.json?dl=1")
    fun getNewFeedList(): Call<NewFeedModel>

    @GET("s/v83n38kvsm6qw62/detail.json?dl=1")
    fun getDetail(): Call<DetailModel>



}
