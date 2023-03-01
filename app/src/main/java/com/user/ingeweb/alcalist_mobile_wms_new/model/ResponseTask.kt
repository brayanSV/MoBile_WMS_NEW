package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName

data class ResponseTask (
  @SerializedName("Status")
  var Status: StatusResponse,
  @SerializedName("Task")
  var Task: ArrayList<Task>,
  @SerializedName("Accompaniment")
  var Accompaniment : ArrayList<Accompaniment>
)