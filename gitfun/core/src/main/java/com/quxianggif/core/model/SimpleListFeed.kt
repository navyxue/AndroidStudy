package com.quxianggif.core.model

import com.google.gson.annotations.SerializedName

/**
 * SimpleListFeed的实体类，用于存储单列列表展示的Feed数据
 * @author xuehj
 * @since 2019-04-23
 */
abstract class SimpleListFeed : BaseFeed() {
    @SerializedName("feed_type")
    var feedType = 0

    abstract fun refFeed() : RefFeed?
}