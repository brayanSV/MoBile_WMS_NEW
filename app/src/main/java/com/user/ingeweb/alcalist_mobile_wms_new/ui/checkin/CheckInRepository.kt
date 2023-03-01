package com.user.ingeweb.alcalist_mobile_wms_new.ui.checkin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.user.ingeweb.alcalist_mobile_wms_new.AppExecutors
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiResponse
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApplicationApi
import com.user.ingeweb.alcalist_mobile_wms_new.model.*
import com.user.ingeweb.alcalist_mobile_wms_new.repository.NetworkBoundResource
import com.user.ingeweb.alcalist_mobile_wms_new.repository.Resource
import com.user.ingeweb.alcalist_mobile_wms_new.utils.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckInRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val applicationApi: ApplicationApi
) {
    fun checkIn(license: License): LiveData<Resource<StatusResponse>> = object: NetworkBoundResource<StatusResponse, ResponseCheckIn>(appExecutors) {
        override fun loadFromDataBase(): LiveData<StatusResponse> {
            return AbsentLiveData.create()
        }

        override fun createCall(): LiveData<ApiResponse<ResponseCheckIn>> {
            Log.e("CheckIn", "llama el api")
            return applicationApi.license(license)
        }

        override fun saveCallResult(item: ResponseCheckIn) {
            Log.e("CheckIn", "response: $item")
        }

        override fun shouldFetch(data: StatusResponse?): Boolean {
            return true
        }

        override fun loadStatus(item: ResponseCheckIn): LiveData<StatusResponse> {
            var statusResponse: MutableLiveData<StatusResponse> = MutableLiveData()
            statusResponse.value = item.Status
            return statusResponse
        }
    }.asLiveData()
}