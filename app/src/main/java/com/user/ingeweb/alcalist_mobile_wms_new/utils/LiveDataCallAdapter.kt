package com.user.ingeweb.alcalist_mobile_wms_new.utils

import android.util.Log
import androidx.lifecycle.LiveData
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<R> (private val responseType: Type): CallAdapter<R, LiveData<ApiResponse<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: retrofit2.Call<R>): LiveData<ApiResponse<R>> {
        return object: LiveData<ApiResponse<R>>() {
            private var started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()

                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: retrofit2.Call<R>, response: Response<R>) {
                            Log.e("datos","${response.body()}")
                            postValue(ApiResponse.create(response))
                        }

                        override fun onFailure(call: retrofit2.Call<R>, t: Throwable) {
                            Log.e("datos","${t.message}")
                            postValue(ApiResponse.create(t))
                        }
                    })
                }
            }
        }
    }
}