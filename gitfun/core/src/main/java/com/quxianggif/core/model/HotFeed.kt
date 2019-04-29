package com.quxianggif.core.model

import com.google.gson.annotations.SerializedName

/**
 * HotFeed的实体类，用于存储服务器返回的热门Feed数据
 * @author xuehj
 * @since 2019-04-23
 */
class HotFeed : WaterFallFeed() {
    @SerializedName("comments_count")
    var commentsCount: Int = 0
}