package com.user.ingeweb.alcalist_mobile_wms_new.api

import androidx.lifecycle.LiveData
import com.user.ingeweb.alcalist_mobile_wms_new.model.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ApplicationApi {
    @POST("License")
    fun license(@Body license: License): LiveData<ApiResponse<ResponseCheckIn>>

    @POST("TaskManger")
    fun task(@Body user: UserRequest): LiveData<ApiResponse<ResponseTask>>
}