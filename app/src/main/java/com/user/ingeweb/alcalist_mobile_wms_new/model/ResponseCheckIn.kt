package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName

data class ResponseCheckIn (
  @SerializedName("Status")
  var Status: StatusResponse
)