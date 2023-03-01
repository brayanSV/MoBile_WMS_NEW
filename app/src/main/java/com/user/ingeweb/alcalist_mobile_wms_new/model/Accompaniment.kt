package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName

data class Accompaniment (
  @SerializedName("TaskId") 
  var TaskId: Int,
  @SerializedName("TareatxtId") 
  var TasktxtId: Int,
  @SerializedName("Codeop") 
  var CodeOp: String,
  @SerializedName("Operators") 
  var Operators: String,
  @SerializedName("Resource") 
  var Resource: String,
  @SerializedName("IsInCharge") 
  var IsInCharge: Boolean
)