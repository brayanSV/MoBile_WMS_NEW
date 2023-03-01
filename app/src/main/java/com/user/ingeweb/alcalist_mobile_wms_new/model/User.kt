package com.user.ingeweb.alcalist_mobile_wms_new.model

import com.google.gson.annotations.SerializedName


data class User (
  @SerializedName("User")
  var User: String = "soporte2",
  @SerializedName("Name")
  var Name: String? = null,
  @SerializedName("Password")
  var Password: String = "temp9015_D",
  @SerializedName("Photo")
  var Photo: String? = null,
  @SerializedName("CompanyId")
  var CompanyId: Int = 1,
  @SerializedName("CodeId")
  var CodeId: Int = 32,
  @SerializedName("GenderId")
  var GenderId: Int? = null
)