/*
 * Copyright (C) guolin, Suzhou Quxiang Inc. Open source codes for study only.
 * Do not use for commercial purpose.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.quxianggif.network.model

import android.graphics.Bitmap

import com.google.gson.annotations.SerializedName
import com.quxianggif.network.request.PostFeedRequest

/**
 * 发布Feed请求的实体类封装。
 *
 * @author guolin
 * @since 17/2/12
 */
class PostFeed : Response() {

    @SerializedName("cover_url")
    var coverUrl: String = ""

    @SerializedName("gif_url")
    var gifUrl: String = ""

    companion object {

        fun getResponse(gifPath: String, feedContent: String, firstFrame: Bitmap, callback: ProgressCallback) {
            PostFeedRequest()
                    .gifPath(gifPath)
                    .feedContent(feedContent)
                    .firstFrame(firstFrame)
                    .listen(callback)
        }
    }

}