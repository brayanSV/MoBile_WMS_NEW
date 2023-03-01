package com.user.ingeweb.alcalist_mobile_wms_new.scan.tq

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.device.ScanDevice

class TQBroadcastReceiver: BroadcastReceiver() {
    private val sm: ScanDevice = ScanDevice()
    val scanAction = "scan.rcv.message"

    var onReceive: ((barCode: String, barCodeLen: Int) -> Unit)? = null

    init {
        sm.outScanMode = 0
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val barOCode = intent?.getByteArrayExtra("barocode")
        val barCodeLen = intent?.getIntExtra("length", 0)
        val barCodeType = intent?.getByteExtra("barcodeType", 0.toByte())
        val barCode =  String(barOCode!!, 0, barCodeLen!!)


        if (barCode != null && barCodeLen >= 0) {
            onReceive?.invoke(barCode, barCodeLen)
        }
    }
}