package com.user.ingeweb.alcalist_mobile_wms_new.scan.tq

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.device.ScanDevice
import androidx.lifecycle.LiveData
import com.user.ingeweb.alcalist_mobile_wms_new.model.ScanResponse


class Scan_TQ(private val context: Context): LiveData<ScanResponse>() {
    private var sm: ScanDevice = ScanDevice()
    private var barcode: String? = null
    private val SCAN_ACTION = "scan.rcv.message"

    override fun onInactive() {
        super.onInactive()

        sm.stopScan()
        context.unregisterReceiver(mScanReceiver)
    }

    override fun onActive() {
        super.onActive()


        sm.outScanMode = 0
        val filter = IntentFilter()
        filter.addAction(SCAN_ACTION)
        context.registerReceiver(mScanReceiver, filter)
    }

    private val mScanReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val barocode = intent.getByteArrayExtra("barocode")
            val barocodelen = intent.getIntExtra("length", 0)
            val temp = intent.getByteExtra("barcodeType", 0.toByte())
            barcode = String(barocode!!, 0, barocodelen)
        }
    }
}