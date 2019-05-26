package test.tamhuynh.com.chappiebot.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.tamhuynh.com.chappiebot.service.model.NewFeedModel
import test.tamhuynh.com.chappiebot.service.model.DetailModel

class NewFeedRepository private constructor() {

    private var service: Service

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(HTTPS_API_DROPBOX_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(Service::class.java)
    }

    fun getNewFeedList(): LiveData<NewFeedModel> {
        val data = MutableLiveData<NewFeedModel>()

        service.getNewFeedList().enqueue(object : Callback<NewFeedModel> {
            override fun onResponse(call: Call<NewFeedModel>, response: Response<NewFeedModel>?) {
                if (response != null) {
                    data.postValue(response.body())
                    Log.d("logs:", "getNewFeedList" + response.body().toString())
                }
            }

            override fun onFailure(call: Call<NewFeedModel>, t: Throwable) {
                data.postValue(null)
                Log.d("logs:", "getNewFeedList:onFailure")
            }
        })

        return data
    }

    fun getDetailModelDetails(document_id: String): LiveData<DetailModel> {
        val data = MutableLiveData<DetailModel>()

        service.getDetail().enqueue(object : Callback<DetailModel> {
            override fun onResponse(call: Call<DetailModel>, response: Response<DetailModel>?) {
                if (response != null) {
                    simulateDelay()
                    data.postValue(response.body())
                    Log.d("logs:", "getDetailModel" + response.body().toString())
                }
            }

            override fun onFailure(call: Call<DetailModel>, t: Throwable) {
                data.postValue(null)

                Log.d("logs:", "getDetailModel:onFailure")
            }
        })
        return data
    }


    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    companion object Factory {
        val instance: NewFeedRepository
            @Synchronized get() {
                return NewFeedRepository()
            }
    }

}
