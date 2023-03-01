package com.user.ingeweb.alcalist_mobile_wms_new.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
  primaryKeys = [
    "TaskIdejec"
  ]
)
data class Task (
  @SerializedName("TaskIdejec") 
  var TaskIdejec: Int,
  @SerializedName("TaskId") 
  var TaskId: Int,
  @SerializedName("ActiveDate") 
  var ActiveDate: String,
  @SerializedName("TaskStatusId") 
  var TaskStatusId: Int,
  @SerializedName("OrderEjec") 
  var OrderEjec: Int,
  @SerializedName("ProfileId") 
  var ProfileId: Int,
  @SerializedName("TaskTypeId") 
  var TaskTypeId: Int,
  @SerializedName("TaskType") 
  var TaskType: String,
  @SerializedName("Delete") 
  var Delete: Int,
  var StatusTaskId: Int
)