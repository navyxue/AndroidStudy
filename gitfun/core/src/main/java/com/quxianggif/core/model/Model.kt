package com.quxianggif.core.model

import org.litepal.crud.LitePalSupport

abstract class Model : LitePalSupport() {
    /**
     * 获取当前实体类的实体数据id，比如User类就获取userId，Coment类就获取commentId
     * @return 当前实体类的实体数据id
     */
    abstract val modelId: Long
}