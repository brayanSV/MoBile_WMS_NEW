package com.user.ingeweb.alcalist_mobile_wms_new.ui.task

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.user.ingeweb.alcalist_mobile_wms_new.AppExecutors
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiResponse
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApplicationApi
import com.user.ingeweb.alcalist_mobile_wms_new.model.ResponseTask
import com.user.ingeweb.alcalist_mobile_wms_new.model.StatusResponse
import com.user.ingeweb.alcalist_mobile_wms_new.model.Task
import com.user.ingeweb.alcalist_mobile_wms_new.model.UserRequest
import com.user.ingeweb.alcalist_mobile_wms_new.repository.NetworkBoundResource
import com.user.ingeweb.alcalist_mobile_wms_new.repository.Resource
import com.user.ingeweb.alcalist_mobile_wms_new.utils.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val applicationApi: ApplicationApi
) {
    fun loadTask(userRequest: UserRequest): LiveData<Resource<List<Task>>> = object: NetworkBoundResource<List<Task>, ResponseTask>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<Task>> {
            return AbsentLiveData.create()
        }

        override fun loadStatus(item: ResponseTask): LiveData<StatusResponse> {
            var statusResponse: MutableLiveData<StatusResponse> = MutableLiveData()
            statusResponse.value = item.Status
            return statusResponse
        }

        override fun createCall(): LiveData<ApiResponse<ResponseTask>> {
            Log.e("Task", "llama el api")
            return applicationApi.task(userRequest)
        }

        override fun saveCallResult(item: ResponseTask) {
            Log.e("Task", "response: $item")
        }

        override fun shouldFetch(data: List<Task>?): Boolean {
            return data == null || data.isEmpty()
        }
    }.asLiveData()
}