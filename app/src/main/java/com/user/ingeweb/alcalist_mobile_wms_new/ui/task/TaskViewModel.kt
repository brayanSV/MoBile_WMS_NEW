package com.user.ingeweb.alcalist_mobile_wms_new.ui.task


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.user.ingeweb.alcalist_mobile_wms_new.model.License
import com.user.ingeweb.alcalist_mobile_wms_new.model.StatusResponse
import com.user.ingeweb.alcalist_mobile_wms_new.model.Task
import com.user.ingeweb.alcalist_mobile_wms_new.model.UserRequest
import com.user.ingeweb.alcalist_mobile_wms_new.repository.Resource
import com.user.ingeweb.alcalist_mobile_wms_new.scan.ScanHelper
import com.user.ingeweb.alcalist_mobile_wms_new.ui.checkin.CheckInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject  constructor(private val scanHelper: ScanHelper, private val taskRepository: TaskRepository, private val checkInRepository: CheckInRepository) : ViewModel() {

    fun registerBroadcastReceiver() {
        scanHelper.registerBroadcastReceiver { model ->
            Log.e("datos","barcode: ${model.BarCode}");
        }
    }

    private val _userRequest: MutableLiveData<UserRequest> = MutableLiveData()
    private val userRequest: LiveData<UserRequest> = _userRequest

    val loadTask: LiveData<Resource<List<Task>>> = taskRepository.loadTask(UserRequest())
    val loadCheckIn: LiveData<Resource<StatusResponse>> = checkInRepository.checkIn(License())


    /*fun onButtonClick() {
        Log.e("datos","me selecciono")
        taskRepository.loadTask(UserRequest()).observe(ViewModelStoreOwner, Observer {

        } )
    }

    fun onButtonClick2() {

    }*/
}