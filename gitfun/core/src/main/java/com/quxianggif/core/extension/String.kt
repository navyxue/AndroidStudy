package com.quxianggif.core.extension

import android.text.TextUtils

/**
 * 字符串操作的工具类
 * @author xuehj
 * @since 2019-04-23
 **/

fun String.getNumbersFromString() = if (TextUtils.isEmpty(this)) {
    ""
} else {
    replace("[^0-9]".toRegex(), "")
}