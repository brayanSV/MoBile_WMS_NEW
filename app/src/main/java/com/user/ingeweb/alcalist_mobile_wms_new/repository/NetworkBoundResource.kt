package com.user.ingeweb.alcalist_mobile_wms_new.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.user.ingeweb.alcalist_mobile_wms_new.AppExecutors
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiEmptyResponse
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiErrorResponse
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiResponse
import com.user.ingeweb.alcalist_mobile_wms_new.api.ApiSuccessResponse
import com.user.ingeweb.alcalist_mobile_wms_new.model.StatusResponse

abstract class NetworkBoundResource <ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDataBase()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    protected abstract fun loadFromDataBase(): LiveData<ResultType>

    @MainThread
    protected abstract fun loadStatus(item: RequestType): LiveData<StatusResponse>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse: LiveData<ApiResponse<RequestType>> = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when(response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIo.execute {
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDataBase()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }

                is ApiEmptyResponse -> {
                    appExecutors.mainThread.execute {
                        result.addSource(loadFromDataBase()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }

                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(newData, response.errorMessage))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}