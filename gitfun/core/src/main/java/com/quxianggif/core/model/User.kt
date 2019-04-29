package com.quxianggif.core.model

import com.google.gson.annotations.SerializedName

/**
 * User实体类，用于存储服务器返回的用户基本信息数据
 * @author xuehj
 * @since 2019-04-23
 */
class User : Model(), SearchItem {
    override val modelId: Long
        get() = userId

    @SerializedName("user_id")
    var userId: Long = 0

    var nickname: String = ""

    var avatar = ""

    @SerializedName("bg_image")
    var bgImage: String = ""

    var description: String = ""

    @SerializedName("followers_count")
    var followersCount: Int = 0

    @SerializedName("followings_count")
    var followingsCount: Int = 0

    @SerializedName("feeds_count")
    var feedsCount: Int = 0

    @SerializedName("is_following")
    var isFollowing: Boolean = false
}