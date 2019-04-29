package com.quxianggif.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 版本更新的实体类封装，如果存在版本更新则会提供下述信息
 * @author xuehj
 * @since 2019-04-23
 */
@Parcelize
class Version(@SerializedName("change_log") val changeLog: String,
              @SerializedName("is_force") val isForce: Boolean,
              val url: String,
              @SerializedName("version_name") val versionName: String,
              @SerializedName("version_code") val versionCode: Int,
              val channel: String) : Parcelable