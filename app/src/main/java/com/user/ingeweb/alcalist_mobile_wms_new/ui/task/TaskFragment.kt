package com.user.ingeweb.alcalist_mobile_wms_new.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.user.ingeweb.alcalist_mobile_wms_new.databinding.TaskFragmentBinding
import com.user.ingeweb.alcalist_mobile_wms_new.utils.ButtonCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class TaskFragment : Fragment() {
    private lateinit var binding: TaskFragmentBinding
    private val viewModel: TaskViewModel by viewModels()


    /*private var sm: ScanDevice = ScanDevice()
    private var barcode: String? = null
    private val SCAN_ACTION = "scan.rcv.message"*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.registerBroadcastReceiver()

        binding.buttonOnclick = object: ButtonCallback {
            override fun Click() {
                viewModel.loadTask.observe(viewLifecycleOwner, Observer {
                    Log.e("datos", "${it.status}, ${it.data}")
                })
            }
        }

        binding.buttonOnclick2 = object: ButtonCallback {
            override fun Click() {
                viewModel.loadCheckIn.observe(viewLifecycleOwner, Observer {
                    Log.e("datos", "${it.status}, ${it.data}")
                })
            }
        }
    }

    override fun onPause() {
        super.onPause()

        /*if(sm != null) {
            sm.stopScan();
        }

        context?.unregisterReceiver(mScanReceiver);*/
    }
}