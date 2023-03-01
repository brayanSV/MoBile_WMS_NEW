package com.user.ingeweb.alcalist_mobile_wms_new.scan

import android.content.Context
import android.content.IntentFilter
import com.user.ingeweb.alcalist_mobile_wms_new.model.ScanResponse
import com.user.ingeweb.alcalist_mobile_wms_new.scan.tq.TQBroadcastReceiver
import javax.inject.Inject

class ScanHelper @Inject constructor(private val context: Context) {
    fun registerBroadcastReceiver(callback: (ScanResponse) -> Unit) {
        val receiver = TQBroadcastReceiver()
        context.registerReceiver(receiver, IntentFilter(receiver.scanAction))

        // Aquí se llama al callback para devolver los datos recibidos
        receiver.onReceive = { barCode, barCodeLen ->
            val model = ScanResponse(barCode, barCodeLen)
            callback(model)
        }
    }
}