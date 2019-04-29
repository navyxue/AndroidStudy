package com.quxianggif.core.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/**
 * Drawable扩展工具类
 * @author xuehj
 * @since 2019-04-23
 **/

fun Drawable.toBitmap(): Bitmap {
    // 取drawable的长宽
    val w = intrinsicWidth
    val h = intrinsicHeight

    // 取drawable的颜色格式
    val config = if (opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
    // 建立对应bitmap
    val bitmap = Bitmap.createBitmap(w, h, config)
    // 建立对应的bitmap画布
    val canvas = Canvas(bitmap)
    setBounds(0, 0, w, h)
    // 把drawable内容画到画布中
    draw(canvas)
    return bitmap
}