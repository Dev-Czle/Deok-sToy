package com.example.androidbaseuiunittest.infinitescrolltest.network

import androidx.lifecycle.MutableLiveData
import okhttp3.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class BaeminRepo {

    var _notice = MutableLiveData<Data>()

    fun loadBaeminNotice(page: Int) {
        val call = BaeminApi.createApi().loadNotice(page.toString())
        call.enqueue(object : Callback<Baemin> {
            override fun onResponse(call: retrofit2.Call<Baemin>, response: Response<Baemin>) {
                if (response.isSuccessful) {
                    _notice.value = response.body()!!.data
                }
            }

            override fun onFailure(call: retrofit2.Call<Baemin>, t: Throwable) = Unit

        })
    }
}