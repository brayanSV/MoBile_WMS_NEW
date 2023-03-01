package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName


data class StatusResponse (
  @SerializedName("Code")
  var Code: String,
  @SerializedName("Message")
  var Message: String
)