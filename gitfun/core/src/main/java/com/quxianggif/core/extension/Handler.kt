package com.quxianggif.core.extension

import android.os.Handler
import android.view.View

/**
 * android.os.Handler的扩展类
 * @author xuehj
 * @since 2019-04-23
 **/

inline fun Handler.postDelayed(delayMillis: Long, crossinline action: () -> Unit): Runnable {
    val runnable = Runnable{action()}
    postDelayed(runnable, delayMillis)
    return runnable
}

inline fun View.postDelayed(delayMillis: Long, crossinline action: () -> Unit): Runnable {
    val runnable = java.lang.Runnable{action()}
    postDelayed(runnable, delayMillis)
    return runnable
}