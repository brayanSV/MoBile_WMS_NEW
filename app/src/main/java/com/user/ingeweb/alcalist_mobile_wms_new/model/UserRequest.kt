package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName


data class UserRequest (
  @SerializedName("License")
  var License: License = License(),
  @SerializedName("User")
  var User: User = User()
)