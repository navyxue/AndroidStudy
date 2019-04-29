package com.quxianggif.core.model

import com.google.gson.annotations.SerializedName

/**
 * UserFeed的实体类，用于存储服务器返回的用户个人主页列表的Feed数据
 * @author xuehj
 * @since 2019-04-23
 */
class UserFeed : SimpleListFeed() {
    override fun refFeed(): RefFeed? {
        return refFeed
    }

    @SerializedName("ref_feed")
    var refFeed: RefFeed? = null
}